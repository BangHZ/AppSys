package com.ybw.appsys.service;

import com.ybw.appsys.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ybw
 * @createDate 2018/10/25
 **/
public interface DataDictionaryService {

    /**
     * 查询所有app的平台信息
     * @return
     */
    List<DataDictionary> findAll();

    /**
     * 查询app状态
     * @return
     */
    List<DataDictionary> getStatus();

    /**
     * 根据tcode查询app的信息
     * @param tcode
     * @return
     */
    List<DataDictionary> getAppFlatForm(@Param("tCode") String tcode);
}
