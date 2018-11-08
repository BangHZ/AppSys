package com.ybw.appsys.service;

import com.ybw.appsys.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author ybw
 * @createDate 2018/10/28
 **/
public interface DevUserService {

    /**
     * 根据用户名查询用户
     * @param devCode
     * @param pwd
     * @return
     */
    DevUser getUserByCondition(@Param("devCode") String devCode, @Param("pwd") String pwd);
}
