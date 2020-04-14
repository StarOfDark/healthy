package com.ncu.healthy.controller;

import com.alibaba.fastjson.JSON;
import com.ncu.healthy.bean.Admin;

import com.ncu.healthy.bean.ResultInfo;
import com.ncu.healthy.bean.User;
import com.ncu.healthy.service.AdminService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String[]> formAdminRegist = request.getParameterMap();
        //2.封装成Admin对象
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin, formAdminRegist);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service
        System.out.println(admin);
        int result = adminService.regist(admin);
        System.out.println("result为：" + result);
        //4.返回结果
        if (result == 0) {
            System.out.println("您已注册");
            response.getWriter().print("您已注册");
        } else if (result == 1) {
            System.out.println("注册成功");
            response.getWriter().print("注册成功");
        } else {
            System.out.println("注册失败");
            response.getWriter().print("注册失败");
        }
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(
            //下面两种传参方式均可，第一种简单
            //@RequestParam(value = "adminName") String adminName,@RequestParam(value = "password") String password,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        //        //1.获取表单参数
        String adminName = request.getParameter("adminName");
        String password = request.getParameter("password");

        //2.查询
        Admin admin = adminService.findByNameAndPwd(adminName, password);
        System.out.println("login方法查询结果admin：" + admin);
        //3.保存查询数据到session
        request.getSession().setAttribute("sessionAdmin", admin);

        //4.判断返回
        if (admin == null) {
            response.getWriter().print("用户名和密码错误");
        } else {
            Object json = JSON.toJSON(admin);
            response.getWriter().print(json);
        }
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping(value = "/getAdminName", method = RequestMethod.GET)
    @ResponseBody
    public void getAdminName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session数据
        Admin admin = (Admin) request.getSession().getAttribute("sessionAdmin");
        Object json = JSON.toJSON(admin);
        //向客户端发送数据
        response.getWriter().print(json);
        //释放资源
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public void getUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin admin = (Admin) request.getSession().getAttribute("sessionAdmin");
        if (admin == null) {
            Json json = null;
            System.out.println("没查到列表");
            response.getWriter().print(json);
            //释放资源在
            response.getWriter().flush();
            response.getWriter().close();
        }
        if (admin != null) {
            List<User> users = adminService.getUserList();
            System.out.println("List列表为：" + users);
            if (request.getSession().getAttribute("sessionUser") == null) {
                //保存第一个user到sessionUser
                request.getSession().setAttribute("sessionUser", users.get(0));
            }
            if (request.getSession().getAttribute("sessionUser") != null) {
                //将users转为json对象
                Object json = JSON.toJSON(users);
                //向客户端发送数据
                response.getWriter().print(json);
                //释放资源
                response.getWriter().flush();
                response.getWriter().close();
            }
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        System.out.println("注销");
        response.sendRedirect("/login.html");
    }

}
