package com.ncu.healthy.service.impl;

import com.ncu.healthy.HealthyApplication;
import com.ncu.healthy.bean.Admin;
import com.ncu.healthy.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HealthyApplication.class)
public class MybatisTest {
    @Autowired
    AdminMapper mapper;



    @Test
    public void test() {
        Admin admin = new Admin();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        admin.setAdminSubDataTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        admin.setAdminID(uuid);
        admin.setAdminName("王五");
        int i = mapper.insertAdmin(admin);
        System.out.println(i);
    }
}
