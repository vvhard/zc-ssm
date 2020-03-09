package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TRolePermission;

import java.util.List;
@Repository
public interface TRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRolePermission record);

    TRolePermission selectByPrimaryKey(Integer id);

    List<TRolePermission> selectAll();

    int updateByPrimaryKey(TRolePermission record);

    void deleteByRoleId(int roleid);
}