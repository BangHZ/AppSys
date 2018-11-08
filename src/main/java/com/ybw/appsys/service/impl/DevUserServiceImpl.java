package com.ybw.appsys.service.impl;

import com.ybw.appsys.mapper.DevUserMapper;
import com.ybw.appsys.pojo.DevUser;
import com.ybw.appsys.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ybw
 * @createDate 2018/10/28
 **/
@Service
public class DevUserServiceImpl implements DevUserService {

    @Autowired
    private DevUserMapper devUserMapper;

    @Override
    public DevUser getUserByCondition(String devCode, String pwd) {
        return devUserMapper.getUserByCondition(devCode,pwd);
    }
}
