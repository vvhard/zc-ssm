package zc.manager.dao;

import java.util.List;
import zc.commons.pojo.TReturnTemp;

public interface TReturnTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReturnTemp record);

    TReturnTemp selectByPrimaryKey(Integer id);

    List<TReturnTemp> selectAll();

    int updateByPrimaryKey(TReturnTemp record);

    List<TReturnTemp> selectByProjectId(int projectTempId);
}