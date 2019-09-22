package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TProjectTag;

import java.util.List;
@Repository
public interface TProjectTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectTag record);

    TProjectTag selectByPrimaryKey(Integer id);

    List<TProjectTag> selectAll();

    int updateByPrimaryKey(TProjectTag record);
}