package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TRole;

import java.util.List;
@Repository
public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    TRole selectByPrimaryKey(Integer id);

    List<TRole> selectAll();

    int updateByPrimaryKey(TRole record);
}