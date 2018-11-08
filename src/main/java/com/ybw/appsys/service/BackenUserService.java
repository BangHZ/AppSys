package com.ybw.appsys.service;

import com.ybw.appsys.pojo.BackendUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author ybw
 * @createDate 2018/10/25
 **/
public interface BackenUserService {

    /**
     * 根据用户名密码查询用户
     * @param userCode
     * @param pwd
     * @return
     */
    BackendUser getUser(@Param("userCode") String userCode, @Param("pwd") String pwd);
}
