package com.ybw.appsys.service.impl;

import com.ybw.appsys.mapper.DataDictionaryMapper;
import com.ybw.appsys.pojo.DataDictionary;
import com.ybw.appsys.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/25
 **/
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private DataDictionaryMapper dictionaryMapper;

    @Override
    public List<DataDictionary> findAll() {
        return dictionaryMapper.findAll();
    }

    @Override
    public List<DataDictionary> getStatus() {
        return dictionaryMapper.getStatus();
    }

    @Override
    public List<DataDictionary> getAppFlatForm(String tcode) {
        return dictionaryMapper.getAppFlatForm(tcode);
    }
}
