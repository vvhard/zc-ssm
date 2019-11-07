package zc.manager.dao;

import java.util.List;
import zc.commons.pojo.TTag;

public interface TTagMapper {
    int insert(TTag record);

    List<TTag> selectAll();

    TTag selectByPrimaryKey(int id);
}