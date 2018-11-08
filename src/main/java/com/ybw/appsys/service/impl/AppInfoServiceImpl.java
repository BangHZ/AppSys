package com.ybw.appsys.service.impl;

import com.ybw.appsys.mapper.AppInfoMapper;
import com.ybw.appsys.pojo.AppInfo;
import com.ybw.appsys.service.AppInfoService;
import com.ybw.appsys.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/25
 **/
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Override
    public List<AppInfo> findAll(Page page) {
        return appInfoMapper.findAll(page);
    }

    @Override
    public int getAppTotalCount() {
        return appInfoMapper.getAppTotalCount();
    }

    @Override
    public List<AppInfo> getAppInfoByCondition(String querySoftwareName, Integer queryFlatformId, Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer queryStatus, Page page) {
        return appInfoMapper.getAppInfoByCondition(querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,queryStatus,page);
    }

    @Override
    public int getTotalCountByCondition(String querySoftwareName, Integer queryFlatformId, Integer queryCategoryLevel1, Integer queryCategoryLevel2, Integer queryCategoryLevel3, Integer queryStatus) {
        return appInfoMapper.getTotalCountByCondition(querySoftwareName,queryFlatformId,queryCategoryLevel1,queryCategoryLevel2,queryCategoryLevel3,queryStatus);
    }

    @Override
    public AppInfo getAppById(Long id) {
        return appInfoMapper.getAppById(id);
    }

    @Override
    public int updateByPrimaryKeySelective(AppInfo record) {
        return appInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public AppInfo getAppByAPKName(String apkName) {
        return appInfoMapper.getAppByAPKName(apkName);
    }

    @Override
    public int insertSelective(AppInfo record) {
        return appInfoMapper.insertSelective(record);
    }
}
