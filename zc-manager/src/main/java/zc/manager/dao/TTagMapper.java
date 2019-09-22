package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TTag;

import java.util.List;
@Repository
public interface TTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTag record);

    TTag selectByPrimaryKey(Integer id);

    List<TTag> selectAll();

    int updateByPrimaryKey(TTag record);
}