package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TPermission;
import zc.commons.pojo.TUser;
import zc.manager.dao.TPermissionMapper;
import zc.manager.service.PermissionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private TPermissionMapper permissionMapper;
    @Override
    public List<TPermission> getUserPermissons(TUser user) {
        List<TPermission> permissions ;
        permissions = permissionMapper.selectByUser(user.getLoginacct());
        if(permissions != null){
            return genWithStructure(permissions);
        }
        return null;
    }

    @Override
    public List<TPermission> getAllPermission() {
        return permissionMapper.selectAll();
    }

    @Override
    public List<TPermission> getRolePermissions(int roleid) {
        return permissionMapper.selectRolePermissions(roleid);
    }

    @Override
    public TPermission getOneById(int id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePermission(TPermission p) {
        return permissionMapper.updateByPrimaryKey(p);
    }

    @Override
    public int addPermission(TPermission p) {
        return permissionMapper.insert(p);
    }

    @Override
    public int deletePermission(int id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    private List<TPermission> genWithStructure(List<TPermission> permissions){
        List<TPermission> menus = new ArrayList<>();
        Map<Integer,TPermission> map = new HashMap<>(); // 方便通过索引查找
        for(TPermission permission:permissions){
            map.put(permission.getId(), permission);
        }
        for(TPermission permission:permissions){
            // 根节点
            if(permission.getPid() == 1){
                menus.add(permission);
            }else if(permission.getPid() != 1 && permission.getPid() !=0){ // 控制面板以及参数管理菜单当作默认存在
                int p_id = permission.getPid();
                TPermission parent = map.get(p_id); // 获取父节点
                List<TPermission> childs = parent.getChilds();
                if(childs != null){
                    childs.add(permission); // 添加当前节点
                }else{
                    childs = new ArrayList<>();
                    childs.add(permission);
                }
                parent.setChilds(childs); // 为父节点设置子孩子
            }
        }
        return menus;
    }
}
