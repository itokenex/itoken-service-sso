package com.funtl.itoken.service.sso.service;


import com.funtl.itoken.service.sso.domain.User;

public interface LoginService {

    public User login(String accountId, String name);
}
