package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TProject;

import java.util.List;
@Repository
public interface TProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProject record);

    TProject selectByPrimaryKey(Integer id);

    List<TProject> selectAll();

    int updateByPrimaryKey(TProject record);
}