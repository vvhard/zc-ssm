package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TToken;

import java.util.List;
@Repository
public interface TTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TToken record);

    TToken selectByPrimaryKey(Integer id);

    List<TToken> selectAll();

    int updateByPrimaryKey(TToken record);
}