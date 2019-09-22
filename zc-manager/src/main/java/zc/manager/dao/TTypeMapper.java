package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TType;

import java.util.List;
@Repository
public interface TTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TType record);

    TType selectByPrimaryKey(Integer id);

    List<TType> selectAll();

    int updateByPrimaryKey(TType record);
}