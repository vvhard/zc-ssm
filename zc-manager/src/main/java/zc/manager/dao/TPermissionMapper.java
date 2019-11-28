package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.pojo.TPermission;

import java.util.List;
@Repository
public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    List<TPermission> selectAll();

    int updateByPrimaryKey(TPermission record);

    List<TPermission> selectByUser(String loginacct);

    List<TPermission> selectRolePermissions(int roleid);

    int updateById(@Param("id")Integer id,@Param("name") String name,@Param("url") String url, @Param("icon")String icon);
}