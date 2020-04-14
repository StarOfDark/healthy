package com.ncu.healthy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ncu.healthy.bean.User;
import com.ncu.healthy.bean.UserData;
import com.ncu.healthy.service.AdminService;
import com.ncu.healthy.service.UserDataService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/UserData")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/setAttribute")
    @ResponseBody
    public void setAttribute(HttpServletRequest request, HttpServletResponse response) {
        //获取请求参数
        String id = request.getParameter("id");
        int count = Integer.parseInt(request.getParameter("count"));
        User user = userDataService.findUserById(id);
        user.setCount(count);
        request.getSession().setAttribute("sessionUser", user);
    }

    /*
    从UserForm页面跳转到Index页面
     */
    @RequestMapping("/changeUser")
    @ResponseBody
    public void changeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        id = id.substring(0, id.length() - 1);
        User user = userDataService.findUserById(id);
        User u = (User) request.getSession().getAttribute("sessionUser");
        user.setCount(u.getCount());
        request.getSession().setAttribute("sessionUser", user);
        Object json = JSON.toJSON(user);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/saveChange")
    @ResponseBody
    public void saveChange(HttpServletRequest request, HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> formUser = request.getParameterMap();
        //Object json = JSON.toJSON(formUser);
        System.out.println(formUser);
        //User user1 = JSON.parseObject((InputStream) formUser,User.class);
        //System.out.println("user为："+user1);
        // JSON.parseObject(JSON.toJSONString(Map<String , String[]>), Class<User> cls);
        User u = new User();
        //将表单提交的map数据封装成user对象
        BeanUtils.populate(u, formUser);

        Integer r = userDataService.updateUser(u);

        if (r == 1) {
            //将更改后的用户信息保存到session
            User user = userDataService.findUserById(u.getId());
            request.getSession().setAttribute("sessionUser", user);
            response.getWriter().print("保存成功！");//前端页面可用弹窗提示返回数据
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            response.getWriter().print("保存失败！");
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    @RequestMapping("/onloadUserForm")
    @ResponseBody
    public void onloadUserForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("sessionUser");
        Object json = JSON.toJSON(user);
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/deletSelectedUser")
    @ResponseBody
    public void deletSelectedUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ids = request.getParameter("ids");
        //以，为分隔符得到id字符串
        String[] id = ids.split(",");
        for (int i = 0; i < id.length; i++) {
               System.out.println(id[i]);
        }
        int r = userDataService.deletSelectedUser(id);
        List<User> userList = adminService.getUserList();
        request.getSession().setAttribute("sessionUser", userList.get(0));
        response.getWriter().print(r);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/findDataById")
    @ResponseBody
    public void findDataById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取点击参数
        User user = (User) request.getSession().getAttribute("sessionUser");
        int count = user.getCount();
        UserData userData = userDataService.findDataById(user.getId(), count);
        //保存计数器和用户id
        count += 1;
        user.setCount(count);
        request.getSession().setAttribute("sessionUser", user);
        //将结果转换成json
        Object json = JSON.toJSON(userData);

        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping("/refreshData")
    @ResponseBody
    public void refreshData(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("sessionUser");
        if (user == null) {
            response.getWriter().print(user);
        }
        if (user != null) {
            findDataById(request, response);

//            int count = user.getCount();
//            UserData userData = userDataService.findDataById(user.getId(), count);
////        保存计数器和用户id
//            count += 1;
//            Object json = JSON.toJSON(userData);
//
//            response.getWriter().print(json);
//            response.getWriter().flush();
//            response.getWriter().close();
        }
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("sessionUser");
        if (request.getSession().getAttribute(user.getId()) == null) {
            String str = "[";
            List<UserData> userDataList = userDataService.findALL(user.getId());
            for (UserData userData : userDataList) {
                str += "[" + userData.getTime() + "," + userData.getEcgData() + "],";
            }
            str = str.substring(0, str.length() - 1);
            str += "]";

            request.getSession().setAttribute(user.getId(), str);
            response.getWriter().print(str);
            response.getWriter().close();
            response.getWriter().flush();
        } else {
            String str = (String) request.getSession().getAttribute(user.getId());
            response.getWriter().print(str);
            response.getWriter().close();
            response.getWriter().flush();
        }
    }
}
