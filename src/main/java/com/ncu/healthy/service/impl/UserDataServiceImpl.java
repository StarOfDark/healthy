package com.ncu.healthy.service.impl;

import com.ncu.healthy.bean.User;
import com.ncu.healthy.bean.UserData;
import com.ncu.healthy.mapper.UserDataMapper;
import com.ncu.healthy.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserDataService")
public class UserDataServiceImpl implements UserDataService {

    @Autowired
    private UserDataMapper userDataMapper;

    @Override
    public User findUserById(String id) {
        return userDataMapper.findUserById(id);
    }

    @Override
    public Integer updateUser(User u) {
        return userDataMapper.updateUser(u);
    }

    @Override
    public int deletSelectedUser(String[] id) {
        return userDataMapper.deletSelectedUser(id);
    }

    @Override
    public UserData findDataById(String id, int count) {
        return userDataMapper.findDataById(id, count);
    }

    @Override
    public List<UserData> findALL(String id) {
        return userDataMapper.findALL(id);
    }
}
