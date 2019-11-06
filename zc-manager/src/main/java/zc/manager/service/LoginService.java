package zc.manager.service;

import zc.commons.pojo.TUser;

public interface LoginService {
    public TUser login(String acct,String password);
    public void logout(TUser user);
}
