package com.ncu.healthy.service.impl;

import com.ncu.healthy.bean.Admin;
import com.ncu.healthy.bean.User;
import com.ncu.healthy.mapper.AdminMapper;
import com.ncu.healthy.mapper.UserDataMapper;
import com.ncu.healthy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserDataMapper userDataMapper;

    @Override
    public Admin findByNameAndPwd(String adminName, String password) {
        return adminMapper.findByNameAndPwd(adminName, password);
    }

    @Override
    public List<User> getUserList() {
        return userDataMapper.findAllUser();
    }


    @Override
    public int regist(Admin admin) {
        Admin a = adminMapper.findByNameAndPwd(admin.getAdminName(), admin.getAdminPwd());
        if(a == null){//数据库无记录，则注册
            admin.setAdminSubDataTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            admin.setAdminID(uuid);
            int result = adminMapper.insertAdmin(admin);
            if(true){
                return 1;//1表示注册成功
            }else{
                return -1;//-1表示失败
            }
        }else {
            return 0;//0表示注册过了
        }

    }


}
