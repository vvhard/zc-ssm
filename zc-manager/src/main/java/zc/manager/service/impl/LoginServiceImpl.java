package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TUser;
import zc.commons.util.MD5Util;
import zc.manager.dao.TUserMapper;
import zc.manager.service.LoginService;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TUserMapper userMapper;
    @Override
    public TUser login(String acct,String password) {
        Map<String,Object> paramMap = new HashMap<>();
        // 数据库内密码字段以MD5密文存放，因此比较前应先将密码转为MD5密文
        String encryptionPassword = MD5Util.digest32(password);
        paramMap.put("loginacct", acct);
        paramMap.put("userpswd", encryptionPassword);
        TUser user = userMapper.selectUserForLogin(paramMap);
        return user;
    }

    @Override
    public void logout(TUser user) {

    }
}
