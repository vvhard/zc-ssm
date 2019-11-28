package zc.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import zc.commons.pojo.TRole;
import zc.commons.pojo.TUserRole;

public interface TRoleMapper {
    int insert(TRole record);

    List<TRole> selectAll();
    // 已分配
    List<TRole> selectAssignToUser(int id);
    // 未分配
    List<TRole> selectUnAssignToUser(int id);

    void deleteRoleFromUr(TUserRole ur);

    void insertRoleToUr(TUserRole ur);

    List<TRole> selectWithCondition(Map<String, Object> map);

    int selectCountWithCondition(Map<String, Object> map);

    int deleteByPrimaryKey(int id);

    int deleteBatch(@Param("roleid") List<Integer> roleid);

    TRole selectByPrimaryKey(int id);

    void updateByPrimaryKey(@Param("id") Integer roleid,@Param("name") String name, @Param("description") String description);
}