package com.ybw.appsys.service;

import com.ybw.appsys.pojo.AppCategory;

import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/25
 **/
public interface AppCategoryService {

    /**
     * 查询分类信息
     * @return
     */
    List<AppCategory> getCategoryByLevel(Integer pId);
}
