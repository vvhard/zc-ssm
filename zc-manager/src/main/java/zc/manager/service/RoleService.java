package zc.manager.service;

import zc.commons.pojo.TRole;
import zc.commons.pojo.TRolePermission;
import zc.commons.pojo.TUserRole;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<TRole> getUserRoles(int id);

    List<TRole> getUnAssignRoles(int id);

    void unAssignForUser(TUserRole ur);

    void assignToUser(TUserRole ur);

    List<TRole> queryData(Map<String, Object> map);

    int queryRoleNums(Map<String, Object> map);

    int deleteRoleById(int id);

    int deleteRoleBatch(int[] roleid);

    TRole getRoleById(int id);

    void addRole(TRole role);

    void updateRoleById(Integer roleid, String name, String description);

    void removeRoleAllPermission(int roleid);

    void assignPermission2Role(TRolePermission rp);
}
