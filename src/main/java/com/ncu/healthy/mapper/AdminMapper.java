package com.ncu.healthy.mapper;

import com.ncu.healthy.bean.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    //查用户
    Admin findByNameAndPwd(@Param("adminName") String adminName,@Param("password") String password);

    int insertAdmin(Admin admin);
}
