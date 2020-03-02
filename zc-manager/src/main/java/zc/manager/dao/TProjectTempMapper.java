package zc.manager.dao;

import java.util.List;
import zc.commons.pojo.TProjectTemp;

public interface TProjectTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectTemp record);

    TProjectTemp selectByPrimaryKey(Integer id);

    List<TProjectTemp> selectAll();

    int updateByPrimaryKey(TProjectTemp record);

    int insertProjectTemp(TProjectTemp projectTemp);
}