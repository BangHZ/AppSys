package com.ybw.appsys.mapper;

import com.ybw.appsys.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppCategory record);

    int insertSelective(AppCategory record);

    AppCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppCategory record);

    int updateByPrimaryKey(AppCategory record);

    /**
     * 查询分类信息
     * @return
     */
    List<AppCategory> getCategoryByLevel(@Param("pId") Integer pId);
}