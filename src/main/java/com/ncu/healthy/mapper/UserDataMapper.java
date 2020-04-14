package com.ncu.healthy.mapper;

import com.ncu.healthy.bean.User;
import com.ncu.healthy.bean.UserData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDataMapper {
    User findUserById(String id);

    List<User> findAllUser();

    Integer updateUser(User u);

    int deletSelectedUser(String[] id);

    UserData findDataById(@Param("id") String id,@Param("count") int count);

    List<UserData> findALL(String id);
}
