package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TProjectType;

import java.util.List;
@Repository
public interface TProjectTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectType record);

    TProjectType selectByPrimaryKey(Integer id);

    List<TProjectType> selectAll();

    int updateByPrimaryKey(TProjectType record);
}