package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TUser;
import zc.manager.dao.TUserMapper;
import zc.manager.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}
