package com.ybw.appsys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ybw.appsys.pojo.*;
import com.ybw.appsys.service.*;
import com.ybw.appsys.tools.Page;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/28
 **/
@RequestMapping("/dev")
@Controller
public class DevController {

	@Autowired
	private DevUserService userService;

	@Autowired
	private AppInfoService appInfoService;

	@Autowired
	private AppVersionService appVersionService;

	@Autowired
	private DataDictionaryService dictionaryService;

	@Autowired
	private AppCategoryService categoryService;

	/**
	 * 用户登录
	 * 
	 * @param devCode
	 * @param devPassword
	 * @param session
	 * @return
	 */
	@RequestMapping("/devLogin")
	public String doLogin(@RequestParam("devCode") String devCode, @RequestParam("devPassword") String devPassword,
			HttpSession session) {
		// 查询用户
		DevUser user = userService.getUserByCondition(devCode, devPassword);
		if (null != user) {
			// 将用户信息存入session
			session.setAttribute("devUserSession", user);
			// 跳转页面
			return "jsp/developer/main";
		}
		// 存入错误信息
		session.setAttribute("error", "用户名或密码错误！");

		return "jsp/devlogin";
	}

	/**
	 * 进入app列表
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/applist")
	public String appList(HttpSession session) {
		// 查询app所属平台
		List<DataDictionary> flatFormList = dictionaryService.findAll();
		// 查询app状态
		List<DataDictionary> statusList = dictionaryService.getStatus();
		// 查询app 分类信息
		List<AppCategory> categoryLevel1List = categoryService.getCategoryByLevel(null);
		// 创建分页对象
		Page page = new Page();
		page.setPageSize(5);
		page.setPageNo(1);
		// 查询app数量
		int totalCount = appInfoService.getAppTotalCount();
		page.setTotalCount(totalCount);

		// 查询app列表
		List<AppInfo> appInfoList = appInfoService.findAll(page);

		// 存入session
		session.setAttribute("appInfoList", appInfoList);
		session.setAttribute("flatFormList", flatFormList);
		session.setAttribute("statusList", statusList);
		session.setAttribute("categoryLevel1List", categoryLevel1List);
		session.setAttribute("page", page);

		return "jsp/developer/appinfolist";
	}

	/**
	 * 查询二、三级分类
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/categorylevellist.json")
	public @ResponseBody String getCategoryLevelList(Integer pid) {
		// 查询app分类信息
		List<AppCategory> categoryLevel1List = categoryService.getCategoryByLevel(pid);

		return JSON.toJSONString(categoryLevel1List);
	}

	/**
	 *
	 *  查询app信息
	 * @param querySoftwareName
	 * @param queryFlatformId
	 * @param queryStatus
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
			@RequestParam(value = "queryStatus", required = false) Integer queryStatus,
			@RequestParam(value = "queryCategoryLevel1", required = false) Integer queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) Integer queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) Integer queryCategoryLevel3,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex, HttpSession session) {

		System.out.println(querySoftwareName);
		// 获取分页对象
		Page page = (Page) session.getAttribute("page");
		// 查询app数量
		int totalCount = appInfoService.getTotalCountByCondition(querySoftwareName, queryFlatformId,
				queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryStatus);
		// 判断分页对象是否为空
		if (null != page) {
			page.setPageNo(pageIndex);
		} else {
			// 为空时创建分页对象
			page = new Page();
			page.setPageNo(1);

			page.setPageSize(5);
		}
		page.setTotalCount(totalCount);
//        System.out.println(querySoftwareName+"："+queryCategoryLevel1+"："+queryCategoryLevel2+"："+queryCategoryLevel3);
		// 查询所有app信息
		List<AppInfo> appList = appInfoService.getAppInfoByCondition(querySoftwareName, queryFlatformId,
				queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, queryStatus, page);

		// 将信息存入session
		session.setAttribute("appInfoList", appList);
		session.setAttribute("page", page);

		session.setAttribute("querySoftwareName", querySoftwareName);
		session.setAttribute("queryFlatformId", queryFlatformId);
		session.setAttribute("queryStatus", queryStatus);
		session.setAttribute("queryCategoryLevel1", queryCategoryLevel1);
		session.setAttribute("queryCategoryLevel2", queryCategoryLevel2);
		session.setAttribute("queryCategoryLevel3", queryCategoryLevel3);

		return "jsp/developer/appinfolist";
	}

	/**
	 * 进入app新增页面
	 * 
	 * @return
	 */
	@RequestMapping("/appinfoadd")
	public String toAppinfoAdd() {
		return "jsp/developer/appinfoadd";
	}

	/**
	 * 查询app所属平台
	 * 
	 * @param tcode
	 * @return
	 */
	@RequestMapping("/datadictionarylist.json")
	public @ResponseBody String getDataDicList(@RequestParam("tcode") String tcode) {
		// 查询所属平台
		List<DataDictionary> dataList = dictionaryService.getAppFlatForm(tcode);
//        System.out.println(tcode);
		return JSONObject.toJSONString(dataList);
	}

	/**
	 * 验证apkname是否已存在
	 * 
	 * @param APKName
	 * @return
	 */
	@RequestMapping("/apkexist.json")
	public @ResponseBody String apkExist(@RequestParam(value = "APKName", required = false) String APKName) {

		// 根据apkname查询app
		AppInfo appinfo = appInfoService.getAppByAPKName(APKName);

		if (null == APKName || "".equals(APKName)) {
			return JSONObject.toJSONString("empty");
		}
		// 判断是否存在
		if (null != appinfo) {
			return JSONObject.toJSONString("exist");
		}

		return JSONObject.toJSONString("noexist");
	}

	/**
	 * 新增app
	 * 
	 * @param appInfo
	 * @param session
	 * @param attach
	 * @return
	 */
	@RequestMapping("/appinfoaddsave")
	public String appInfoSave(AppInfo appInfo, HttpSession session,
			@RequestParam(value = "a_logoPicPath", required = false) MultipartFile attach) {
		// 补全app信息
		appInfo.setUpdatedate(new Date());
		appInfo.setCreationdate(new Date());
		// 获取session中的用户数据
		DevUser user = (DevUser) session.getAttribute("devUserSession");
		appInfo.setCreatedby(user.getId());
		appInfo.setDevid(user.getId());

		// 文件上传
		String idPicPath = null;
//        System.out.println("当前项目所在路径："+this.getClass().getClassLoader().getResource("").getPath());
		// 判断文件是否为空
		if (!attach.isEmpty()) {
			// 定义上传路径
			String path = session.getServletContext().getRealPath("static" + File.separator + "uploadfiles");
			System.out.println("上传路径：" + path);
			// 原文件名
			String oldFileName = attach.getOriginalFilename();
			System.out.println("原文件名：" + oldFileName);
			// 原文件后缀
			String prefix = FilenameUtils.getExtension(oldFileName);
			System.out.println("原文件后缀：" + prefix);
			int filesize = 500000;
			System.out.println("原文件大小：" + attach.getSize());
			// 上传文件大小不得超过500KB
			if (attach.getSize() > filesize) {
				session.setAttribute("fileUploadError", "上传文件大小不得超过500KB");
				return "jsp/developer/appinfoadd";
			} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
					|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) { // 判断文件格式
				// 文件新名称
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt() + "_29.jpg";
				System.out.println("文件新名称：" + fileName);
				// 定义上传对象
				File targetFile = new File(path, fileName);
				// 判断上传的路径是否存在
				if (!targetFile.exists()) {
					targetFile.getParentFile().mkdirs();
				}
				// 上传
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("fileUploadError", "上传失败！");
					return "jsp/developer/appinfoadd";
				}
				idPicPath = path + File.separator + fileName;
			} else {
				session.setAttribute("fileUploadError", "文件格式不正确！");
				return "jsp/developer/appinfoadd";
			}
		}

		appInfo.setLogolocpath(idPicPath);
		appInfo.setLogopicpath(idPicPath);
		// 执行新增方法
		appInfoService.insertSelective(appInfo);

		return "redirect:/dev/applist";
	}

	/**
	 * 查看app信息
	 * @param appinfoid
	 * @param session
	 * @return
	 */
	@RequestMapping("/appview/{appinfoid}")
	public String toAppInfoView(@PathVariable Long appinfoid, HttpSession session) {

		// 查询app信息
		AppInfo appInfo = appInfoService.getAppById(appinfoid);
		// 查询该app的版本信息
		List<AppVersion> appVersionList = appVersionService.getAppversionListById(appinfoid);
		// 将信息存入session
		session.setAttribute("appInfo", appInfo);
		session.setAttribute("appVersionList", appVersionList);

		return "jsp/developer/appinfoview";
	}
	
	@RequestMapping("/appinfomodify/{id}")
	public String toAppInfoModify() {
		return "jsp/developer/appinfo";
	}
	

}
