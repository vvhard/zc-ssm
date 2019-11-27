package zc.manager.service;

import zc.commons.pojo.TUser;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<TUser> queryData(Map<String, Object> map);

    int queryUserNums(Map<String, Object> map);

    int deleteUser(int userid);
    int deleteUserBatch(int[] userid);

    TUser getOne(int id);

    void addUser(TUser user);

    void updateUser(String loginacct, String username, String email);
}
