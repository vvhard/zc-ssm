package zc.manager.dao;

import java.util.List;
import zc.commons.pojo.TUserRole;

public interface TUserRoleMapper {
    int insert(TUserRole record);

    List<TUserRole> selectAll();
}