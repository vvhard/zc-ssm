package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TReturn;

import java.util.List;
@Repository
public interface TReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReturn record);

    TReturn selectByPrimaryKey(Integer id);

    List<TReturn> selectAll();

    int updateByPrimaryKey(TReturn record);
}