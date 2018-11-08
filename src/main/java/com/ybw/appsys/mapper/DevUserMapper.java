package com.ybw.appsys.mapper;

import com.ybw.appsys.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DevUser record);

    int insertSelective(DevUser record);

    DevUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DevUser record);

    int updateByPrimaryKey(DevUser record);

    /**
     * 根据用户名查询用户
     * @param devCode
     * @param pwd
     * @return
     */
    DevUser getUserByCondition(@Param("devCode") String devCode, @Param("pwd") String pwd);
}