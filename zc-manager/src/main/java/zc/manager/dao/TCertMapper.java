package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TCert;

import java.util.List;
@Repository
public interface TCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCert record);

    TCert selectByPrimaryKey(Integer id);

    List<TCert> selectAll();

    int updateByPrimaryKey(TCert record);
}