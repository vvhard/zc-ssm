package zc.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import zc.commons.pojo.TCert;

public interface TCertMapper {
    int insert(TCert record);

    List<TCert> selectAll();

    List<TCert> selectWithConditon(Map<String, Object> map);

    int selectCountWithCondition(Map<String, Object> map);

    void deleteByPrimaryKey(int id);

    TCert selectByPrimaryKey(int id);

    void update(TCert cert);

    void deleteBatchByPrimaryKey(@Param("certid")List<Integer> certid);
}