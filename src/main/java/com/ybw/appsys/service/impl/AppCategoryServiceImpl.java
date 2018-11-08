package com.ybw.appsys.service.impl;

import com.ybw.appsys.mapper.AppCategoryMapper;
import com.ybw.appsys.pojo.AppCategory;
import com.ybw.appsys.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/25
 **/
@Service
public class AppCategoryServiceImpl implements AppCategoryService {

    @Autowired
    private AppCategoryMapper categoryMapper;

    @Override
    public List<AppCategory> getCategoryByLevel(Integer pId) {
        return categoryMapper.getCategoryByLevel(pId);
    }
}
