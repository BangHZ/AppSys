package com.ybw.appsys.mapper;

import com.ybw.appsys.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppVersionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    /**
     * 查询指定 app版本
     * @param id
     * @return
     */
    AppVersion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    /**
     * 根据appid查询版本信息
     * @param appId
     * @return
     */
    List<AppVersion> getAppversionListById(@Param("appId") Long appId);
}