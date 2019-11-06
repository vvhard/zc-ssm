package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMessage;

import java.util.List;
import java.util.Map;

@Repository
public interface TMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMessage record);

    TMessage selectByPrimaryKey(Integer id);

    List<TMessage> selectAll();

    int updateByPrimaryKey(TMessage record);

    List<TMessage> selectWithCondition(Map<String, Object> map);

    int selectCountWithCondition(Map<String, Object> map);
}