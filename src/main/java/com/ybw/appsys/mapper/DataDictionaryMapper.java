package com.ybw.appsys.mapper;

import com.ybw.appsys.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.List;

public interface DataDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);

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