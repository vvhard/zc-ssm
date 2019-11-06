package zc.manager.service;

import zc.commons.pojo.TPermission;
import zc.commons.pojo.TUser;

import java.util.List;

public interface PermissionService {
    public List<TPermission> getUserPermissons(TUser user);

    List<TPermission> getAllPermission();

    List<TPermission> getRolePermissions(int roleid);

    TPermission getOneById(int id);

    int updatePermission(TPermission p);

    int addPermission(TPermission p);

    int deletePermission(int id);
}
