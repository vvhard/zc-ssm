package zc.manager.dao;

import java.util.List;
import java.util.Map;

import zc.commons.pojo.TRole;
import zc.commons.pojo.TUserRole;

public interface TRoleMapper {
    int insert(TRole record);

    List<TRole> selectAll();

    List<TRole> selectAssignToUser(int id);

    List<TRole> selectUnAssignToUser(int id);

    void deleteRoleFromUr(TUserRole ur);

    void insertRoleToUr(TUserRole ur);

    List<TRole> selectWithCondition(Map<String, Object> map);

    int selectCountWithCondition(Map<String, Object> map);

    int deleteByPrimaryKey(int id);

    int deleteBatch(List<Integer> list);

    TRole selectByPrimaryKey(int id);
}