package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TUser;

import java.util.List;
import java.util.Map;

@Repository("t")
public interface TUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    TUser selectByPrimaryKey(Integer id);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);

    TUser selectUserForLogin(Map<String, Object> map);
}