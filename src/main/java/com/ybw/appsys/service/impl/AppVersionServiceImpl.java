package com.ybw.appsys.service.impl;

import com.ybw.appsys.mapper.AppVersionMapper;
import com.ybw.appsys.pojo.AppVersion;
import com.ybw.appsys.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/26
 **/
@Service
public class AppVersionServiceImpl implements AppVersionService {

    @Autowired
    private AppVersionMapper versionMapper;

    @Override
    public AppVersion selectByPrimaryKey(Long id) {
        return versionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AppVersion> getAppversionListById(Long appId) {
        return versionMapper.getAppversionListById(appId);
    }
}
