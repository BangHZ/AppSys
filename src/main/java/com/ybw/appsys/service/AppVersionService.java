package com.ybw.appsys.service;

import com.ybw.appsys.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/26
 **/
public interface AppVersionService {

    /**
     * 查询指定 app版本
     * @param id
     * @return
     */
    AppVersion selectByPrimaryKey(Long id);

    /**
     * 根据appid查询版本信息
     * @param appId
     * @return
     */
    List<AppVersion> getAppversionListById(@Param("appId") Long appId);
}
