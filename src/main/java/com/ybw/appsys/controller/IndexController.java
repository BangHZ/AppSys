package com.ybw.appsys.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ybw
 * @createDate 2018/10/25
 **/
@Controller
public class IndexController {

    /**
     * 进入主页面
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 进入后台管理登陆页面
     * @return
     */
    @RequestMapping("/manager/login")
    public String managerIndex(){
        return "jsp/backendlogin";
    }

    @RequestMapping("/manager/logout")
    public String logout(HttpSession session){
        // 清除session
        session.removeAttribute("userSession");

        return "index";
    }

    /**
     * 进入开发者平台登录界面
     * @return
     */
    @RequestMapping("/dev/login")
    public String devIndex(){
        return "jsp/devlogin";
    }
}
