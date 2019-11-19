package com.funtl.itoken.service.sso.service.impl;


import com.funtl.itoken.service.sso.domain.User;
import com.funtl.itoken.service.sso.mapper.UserMapper;
import com.funtl.itoken.service.sso.service.LoginService;
import com.funtl.itoken.service.sso.service.consumer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public User login(String accountId, String name) {

        User user = null;
        Object o = redisService.getData(accountId);
        if(o==null){
            Example example = new Example(User.class);
            System.out.println();
            example.createCriteria().andEqualTo("accountId",accountId).andEqualTo("name",name);
            user = userMapper.selectOneByExample(example);
            if(user!=null){
                redisService.put(accountId,name,24*60*60);
                return user;
            }else{
                System.out.println("数据库查询不到此用户");
                return null;
            }
        }else {
            System.out.println("redis不为空:"+o);
            user=new User();
            user.setAccountId(accountId);
            user.setName(name);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            //user = (User) o;
        }


        return user;
    }
}
