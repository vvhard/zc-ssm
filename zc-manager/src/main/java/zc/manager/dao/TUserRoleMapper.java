package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TUserRole;

import java.util.List;
@Repository
public interface TUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    TUserRole selectByPrimaryKey(Integer id);

    List<TUserRole> selectAll();

    int updateByPrimaryKey(TUserRole record);
}