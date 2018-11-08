package com.ybw.appsys.controller;

import com.alibaba.fastjson.JSON;
import com.ybw.appsys.pojo.*;
import com.ybw.appsys.service.*;
import com.ybw.appsys.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/25
 * @desc 后台管理 控制器
 **/
@RequestMapping("/manager")
@Controller
public class ManagerController {

    @Autowired
    private BackenUserService userService;

    @Autowired
    private AppInfoService appInfoService;

    @Autowired
    private DataDictionaryService dictionaryService;

    @Autowired
    private AppCategoryService categoryService;

    @Autowired
    private AppVersionService appVersionService;

    /**
     * 后台管理系统登陆
     * @return
     */
    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam("userCode") String userCode, @RequestParam("userPassword") String userPassword, HttpSession session){

        // 查询用户
        BackendUser user = userService.getUser(userCode,userPassword);
        // 判断是否存在
        if(null != user){
            // 将用户信息存入session
            session.setAttribute("userSession",user);
            return "jsp/backend/main";
        }
        // 错误信息
        session.setAttribute("error","用户名或密码错误！");
        return "redirect:/jsp/backendlogin.jsp";
    }

    /**
     * 跳转applist页面
     * @param session
     * @return
     */
    @RequestMapping("/appList")
    public String toAppList(HttpSession session){

        // 分页信息
        Page page = new Page();

        page.setPageNo(1);
        page.setPageSize(5);

        // 查询app信息总数量
        int totalCount = appInfoService.getAppTotalCount();
        page.setTotalCount(totalCount);

        // 查询app分类信息
        List<AppCategory> categoryLevel1List = categoryService.getCategoryByLevel(null);
        // 查询app平台
        List<DataDictionary> flatFormList = dictionaryService.findAll();
        // 查询所有app信息
        List<AppInfo> appList = appInfoService.findAll(page);


        // 将信息存入session
        session.setAttribute("appInfoList", appList);
        session.setAttribute("flatFormList", flatFormList);
        session.setAttribute("categoryLevel1List", categoryLevel1List);

        session.setAttribute("page", page);
        return "jsp/backend/applist";
    }

    /**
     * 查询二、三级分类
     * @param pid
     * @return
     */
    @RequestMapping("/categorylevellist.json")
    public @ResponseBody String getCategoryLevelList(Integer pid){
        // 查询app分类信息
        List<AppCategory> categoryLevel1List = categoryService.getCategoryByLevel(pid);

        return JSON.toJSONString(categoryLevel1List);
    }

    /**
     * app查询
     * @param querySoftwareName
     * @param queryFlatformId
     * @param queryCategoryLevel1
     * @param queryCategoryLevel2
     * @param queryCategoryLevel3
     * @param pageIndex
     * @param session
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(value = "querySoftwareName", defaultValue = "") String querySoftwareName,
                       @RequestParam(value = "queryFlatformId", required = false) Integer queryFlatformId,
                       @RequestParam(value = "queryCategoryLevel1", required = false) Integer queryCategoryLevel1,
                       @RequestParam(value = "queryCategoryLevel2", required = false) Integer queryCategoryLevel2,
                       @RequestParam(value = "queryCategoryLevel3", required = false) Integer queryCategoryLevel3,
                       @RequestParam(value = "pageIndex", required = false) Integer pageIndex, HttpSession session){

        // 获取分页对象
        Page page = (Page) session.getAttribute("page");
        // 查询app数量
        int totalCount = appInfoService.getTotalCountByCondition(querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,0);
        // 判断分页对象是否为空
        if(null != page){
            page.setPageNo(pageIndex);
        }else{
            // 为空时创建分页对象
            page = new Page();
            page.setPageNo(1);

            page.setPageSize(5);
        }
        page.setTotalCount(totalCount);
//        System.out.println(querySoftwareName+"："+queryCategoryLevel1+"："+queryCategoryLevel2+"："+queryCategoryLevel3);
        // 查询所有app信息
        List<AppInfo> appList = appInfoService.getAppInfoByCondition(querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,0,page);

        // 将信息存入session
        session.setAttribute("appInfoList", appList);
        session.setAttribute("page", page);

        session.setAttribute("querysoftwarename", querySoftwareName);
        session.setAttribute("queryFlatformId", queryFlatformId);
        session.setAttribute("queryCategoryLevel1", queryCategoryLevel1);
        session.setAttribute("queryCategoryLevel2", queryCategoryLevel2);
        session.setAttribute("queryCategoryLevel3", queryCategoryLevel3);

        return "jsp/backend/applist";
    }

    /**
     * 进入 app信息审核页面
     * @param appId
     * @param vId
     * @param session
     * @return
     */
    @RequestMapping("/check")
    public String check(@RequestParam("aid") Long appId,@RequestParam("vid") Long vId,HttpSession session){
        // 根据 id 查询app信息
        AppInfo appInfo = appInfoService.getAppById(appId);

        // 查询app版本信息
        AppVersion appVersion = appVersionService.selectByPrimaryKey(vId);

        // 存入session
        session.setAttribute("appInfo", appInfo);
        session.setAttribute("appVersion", appVersion);

        return "jsp/backend/appcheck";
    }

    /**
     * 审核app
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/checksave")
    public String checkSave(@RequestParam("id") Long id, @RequestParam("status") Long status){
        // 创建条件类
        AppInfo appInfo = new AppInfo();
        appInfo.setId(id);
        appInfo.setStatus(status);
        appInfo.setUpdatedate(new Date());
        appInfo.setModifydate(new Date());
        appInfo.setModifyby(id);
        // 修改app的状态
        appInfoService.updateByPrimaryKeySelective(appInfo);

        return "redirect:/manager/appList";
    }



}
