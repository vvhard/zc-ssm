package zc.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import zc.commons.pojo.TProjectTemp;

public interface TProjectTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectTemp record);

    TProjectTemp selectByPrimaryKey(Integer id);

    List<TProjectTemp> selectAll();

    int updateByPrimaryKey(TProjectTemp record);

    int insertProjectTemp(TProjectTemp projectTemp);

    void insertPayInfo(@Param("project_temp_id") int project_temp_id,
                       @Param("app_id")String app_id,
                       @Param("app_private_key")String app_private_key,
                       @Param("alipay_public_key")String alipay_public_key);

    List<TProjectTemp> selectByMap(Map<String, Object> map);

    int selectProjectCount();

    void updateStatus(@Param("project_temp_id") int project_temp_id,@Param("status") String status);
}