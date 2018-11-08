package com.ybw.appsys.service.impl;

import com.ybw.appsys.mapper.BackendUserMapper;
import com.ybw.appsys.pojo.BackendUser;
import com.ybw.appsys.service.BackenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ybw
 * @createDate 2018/10/25
 **/
@Service
public class BackendUserServiceImpl implements BackenUserService {

    @Autowired
    private BackendUserMapper userMapper;

    @Override
    public BackendUser getUser(String userCode, String pwd) {
        return userMapper.getUser(userCode,pwd);
    }
}
