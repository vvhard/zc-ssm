package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMessage;

import java.util.List;
@Repository
public interface TMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMessage record);

    TMessage selectByPrimaryKey(Integer id);

    List<TMessage> selectAll();

    int updateByPrimaryKey(TMessage record);
}