package com.ybw.appsys.mapper;

import com.ybw.appsys.pojo.AppInfo;
import com.ybw.appsys.tools.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AppInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppInfo record);

    /**
     * 新增app信息
     * @param record
     * @return
     */
    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Long id);

    /**
     * 修改app信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);

    /**
     * 查询全部并分页显示
     * @param page
     * @return
     */
    List<AppInfo> findAll(@Param("page") Page page);

    /**
     *
     * @return
     */
    int getAppTotalCount();

    /**
     * 根据各种条件查询app信息
     * @param querySoftwareName
     * @param queryFlatformId
     * @param queryCategoryLevel1
     * @param queryCategoryLevel2
     * @param queryCategoryLevel3
     * @param page
     * @return
     */
    List<AppInfo> getAppInfoByCondition(@Param("querySoftwareName") String querySoftwareName, @Param("queryFlatformId") Integer queryFlatformId,
                           @Param("queryCategoryLevel1") Integer queryCategoryLevel1, @Param("queryCategoryLevel2") Integer queryCategoryLevel2,
                           @Param("queryCategoryLevel3") Integer queryCategoryLevel3, @Param("queryStatus") Integer queryStatus,
                           @Param("page") Page page);

    /**
     * 根据条件查询app数量
     * @param querySoftwareName
     * @param queryFlatformId
     * @param queryCategoryLevel1
     * @param queryCategoryLevel2
     * @param queryCategoryLevel3
     * @return
     */
    int getTotalCountByCondition(@Param("querySoftwareName") String querySoftwareName, @Param("queryFlatformId") Integer queryFlatformId,
                                 @Param("queryCategoryLevel1") Integer queryCategoryLevel1, @Param("queryCategoryLevel2") Integer queryCategoryLevel2,
                                 @Param("queryCategoryLevel3") Integer queryCategoryLevel3, @Param("queryStatus") Integer queryStatus);

    /**
     * 根据id 查询app 信息
     * @param id
     * @return
     */
    AppInfo getAppById(Long id);

    /**
     * 根据apkName查询app是否存在
     * @param apkName
     * @return
     */
    AppInfo getAppByAPKName(@Param("apkName") String apkName);
}