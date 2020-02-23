package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TUser;
import zc.commons.util.MD5Util;
import zc.manager.dao.TUserMapper;
import zc.manager.service.UserService;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserMapper userMapper;
    @Override
    public List<TUser> queryData(Map<String, Object> map) {
        return  userMapper.selectWithCondition(map);
    }

    @Override
    public int queryUserNums(Map<String, Object> map) {
        return userMapper.selectCountWithCondition(map);
    }

    @Override
    public int deleteUser(int userid) {

        return userMapper.deleteByUserId(userid);
    }

    @Override
    public int deleteUserBatch(int[] userid) {
        List<Integer> list = new ArrayList<>();
        for (Integer e : userid) {
            list.add(e);
        }
        return userMapper.deleteBatchByUserId(list);
    }

    @Override
    public TUser getOne(int id) {

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addUser(TUser user) {
        String encryptionPassword = MD5Util.digest32("123456"); // 初始密码默认为123456
        user.setUserpswd(encryptionPassword);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        user.setCreatetime(format.format(date));
        userMapper.insert(user);
    }

    @Override
    public void updateUser(String loginacct, String username, String email) {
        userMapper.updateByLoginAcct(loginacct,username,email);
    }
}
