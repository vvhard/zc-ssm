package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TParam;

import java.util.List;
@Repository
public interface TParamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TParam record);

    TParam selectByPrimaryKey(Integer id);

    List<TParam> selectAll();

    int updateByPrimaryKey(TParam record);
}