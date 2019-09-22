package zc.manager.dao;

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
}