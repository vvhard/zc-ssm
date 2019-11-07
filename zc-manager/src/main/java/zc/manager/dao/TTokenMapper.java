package zc.manager.dao;

import java.util.List;
import zc.commons.pojo.TToken;

public interface TTokenMapper {
    int insert(TToken record);

    List<TToken> selectAll();
}