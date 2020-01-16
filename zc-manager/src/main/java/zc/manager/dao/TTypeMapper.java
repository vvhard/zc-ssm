package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.pojo.TType;

import java.util.List;
import java.util.Map;

@Repository
public interface TTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TType record);

    TType selectByPrimaryKey(Integer id);

    List<TType> selectAll();

    int updateByPrimaryKey(TType record);

    int selectCountWithCondition(Map<String, Object> map);

    List<TType> selectWithCondition(Map<String, Object> map);

    void delBatch(@Param("typeid") Integer[] typeid);
}