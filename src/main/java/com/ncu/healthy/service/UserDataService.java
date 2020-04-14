package com.ncu.healthy.service;

import com.ncu.healthy.bean.User;
import com.ncu.healthy.bean.UserData;

import java.util.List;

public interface UserDataService {
    User findUserById(String id);

    Integer updateUser(User u);

    int deletSelectedUser(String[] id);

    UserData findDataById(String id, int count);

    List<UserData> findALL(String id);
}
