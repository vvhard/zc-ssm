package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.pojo.TUser;

import java.util.List;
import java.util.Map;

@Repository
public interface TUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    TUser selectByPrimaryKey(Integer id);

    List<TUser> selectAll();

    int updateByPrimaryKey(TUser record);

    TUser selectUserForLogin(Map<String, Object> map);

    int selectCountWithCondition(Map<String, Object> map);

    List<TUser> selectWithCondition(Map<String, Object> map);

    int deleteBatchByUserId(@Param("userid") List<Integer> userid);

    int deleteByUserId(int userid);

    void updateByLoginAcct(@Param("loginacct")String loginacct, @Param("username")String username,@Param("email") String email);
}