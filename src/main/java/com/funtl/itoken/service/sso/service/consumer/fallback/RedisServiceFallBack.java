package com.funtl.itoken.service.sso.service.consumer.fallback;


import com.funtl.itoken.service.sso.service.consumer.RedisService;

import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallBack implements RedisService {
    @Override
    public String put(String key, String value, long seconds) {
        //return "put 502";
        return null;
    }

    @Override
    public String putData(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String getData(String key) {
        //return FallBack.badGateway();
        return null;
    }

    @Override
    public String hello() {
        return "hello fallback";
    }
}
