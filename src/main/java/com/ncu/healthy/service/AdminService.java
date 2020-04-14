package com.ncu.healthy.service;

import com.ncu.healthy.bean.Admin;
import com.ncu.healthy.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {
    Admin findByNameAndPwd(String adminName, String password);

    List<User> getUserList();

    int regist(Admin admin);
}
