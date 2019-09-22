package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMemeber;

import java.util.List;
@Repository
public interface TMemeberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemeber record);

    TMemeber selectByPrimaryKey(Integer id);

    List<TMemeber> selectAll();

    int updateByPrimaryKey(TMemeber record);
}