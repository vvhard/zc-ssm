package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.pojo.TRole;
import zc.commons.pojo.TUserRole;

import java.util.List;
import java.util.Map;

@Repository
public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    TRole selectByPrimaryKey(Integer id);

    List<TRole> selectAll();

    int updateByPrimaryKey(TRole record);

    List<TRole> selectAssignToUser(int id);

    List<TRole> selectUnAssignToUser(int id);

    void deleteRoleFromUr(TUserRole ur);

    void insertRoleToUr(TUserRole ur);

    List<TRole> selectWithCondition(Map<String, Object> map);

    int selectCountWithCondition(Map<String, Object> map);

    int deleteBatch(@Param("roleid") List<Integer> roleid);
}