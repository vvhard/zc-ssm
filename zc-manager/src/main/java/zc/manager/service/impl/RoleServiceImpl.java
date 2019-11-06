package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TRole;
import zc.commons.pojo.TUserRole;
import zc.manager.dao.TRoleMapper;
import zc.manager.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private TRoleMapper roleMapper;
    @Override
    public List<TRole> getUserRoles(int id) {
        return roleMapper.selectAssignToUser(id);
    }

    @Override
    public List<TRole> getUnAssignRoles(int id) {
        return roleMapper.selectUnAssignToUser(id);
    }

    @Override
    public void unAssignForUser(TUserRole ur) {
        roleMapper.deleteRoleFromUr(ur);
    }

    @Override
    public void assignToUser(TUserRole ur) {
        roleMapper.insertRoleToUr(ur);
    }

    @Override
    public List<TRole> queryData(Map<String, Object> map) {
        return roleMapper.selectWithCondition(map);
    }

    @Override
    public int queryRoleNums(Map<String, Object> map) {
        return roleMapper.selectCountWithCondition(map);
    }

    @Override
    public int deleteRoleById(int id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteRoleBatch(int[] roleid) {
        List<Integer> list = new ArrayList<>();
        for (Integer e : list) {
            list.add(e);
        }
        return roleMapper.deleteBatch(list);
    }

    @Override
    public TRole getRoleById(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
