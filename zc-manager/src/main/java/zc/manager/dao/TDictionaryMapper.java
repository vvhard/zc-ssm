package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TDictionary;

import java.util.List;
@Repository
public interface TDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDictionary record);

    TDictionary selectByPrimaryKey(Integer id);

    List<TDictionary> selectAll();

    int updateByPrimaryKey(TDictionary record);
}