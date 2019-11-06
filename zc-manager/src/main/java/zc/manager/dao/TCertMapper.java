package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TCert;

import java.util.List;
import java.util.Map;

@Repository
public interface TCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCert record);

    TCert selectByPrimaryKey(Integer id);

    List<TCert> selectAll();

    int updateByPrimaryKey(TCert record);

    List<TCert> selectWithConditon(Map<String, Object> map);

    int selectCountWithCondition(Map<String, Object> map);
}