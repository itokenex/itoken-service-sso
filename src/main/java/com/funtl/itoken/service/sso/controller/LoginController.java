package com.funtl.itoken.service.sso.controller;


import com.funtl.itoken.commom.utils.MapperUtils;
import com.funtl.itoken.service.sso.domain.User;
import com.funtl.itoken.service.sso.service.LoginService;
import com.funtl.itoken.service.sso.service.consumer.RedisService;
import com.funtl.itoken.service.sso.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisService redisService;

    public static Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(String url,HttpServletRequest request,Model model){
        String token = CookieUtils.getCookieValue(request, "token");
        if(StringUtils.isNotBlank(token)){
            String data = redisService.getData(token);//accountId
            if(StringUtils.isNotBlank(data)){
                String name = redisService.getData(data);
                if(StringUtils.isNotBlank(name)) {
                    try {
                        User user = new User();
                        user.setAccountId(data);
                        user.setName(name);
                        if (StringUtils.isNotBlank(url)) {
                            return "redirect:" + url;
                        }
                        model.addAttribute("user", user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(StringUtils.isNotBlank(url)){
            model.addAttribute("url",url);
        }
        return "login";
    }

    /**
     * 登录操作
     * @param accountId
     * @param name
     * @return
     */

    @RequestMapping(value = "loginPost", method= RequestMethod.POST)
    public String login(String accountId, String name, String url, Model model,
                        RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response){
        User login = loginService.login(accountId, name);
        System.out.println("sso:"+login);
        System.out.println("url");
        if(login!=null){//登录成功
            String token = UUID.randomUUID().toString();
            String put = redisService.put(token, accountId, 24 * 60 * 60);
            if(put!=null) {
                CookieUtils.setCookie(request, response, "token", token);
                model.addAttribute("message", "登陆成功");
                if (StringUtils.isNotBlank(url)) {
                    model.addAttribute("url",url);
                    return "redirect:" + url;
                }
            }else{
                redirectAttributes.addFlashAttribute("message","服务器异常");
            }
        }else{
            //model.addFlashAttribute("message","用户名或密码错误");
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
        }

        if (StringUtils.isNotBlank(url)) {
            model.addAttribute("url",url);
        }
        return "redirect:/login";
    }

    /**
     * 登出
     * @param url
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="logout",method = RequestMethod.GET)
    public String logout(String url, Model model,HttpServletRequest request, HttpServletResponse response){

        CookieUtils.deleteCookie(request,response,"token");

        return login(url,request,model);
    }


    @RequestMapping(value = "loginTest", method= RequestMethod.GET)
    public String login(String accountId, Model model){
        String o = null;
        try {
            o = redisService.getData(accountId);
            System.out.println("loginTest:"+o);
        }catch(Exception e){
            logger.info("触发熔断...");
            e.printStackTrace();
        }

        if(o!=null) {
            model.addAttribute("message", o);
        }else{
            model.addAttribute("", "获取失败");
        }
        return "login";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return redisService.hello();
    }
}
