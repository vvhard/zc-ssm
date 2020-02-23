package zc.api.service;

import zc.commons.pojo.TMember;

public interface MemberService {
    /**
     * 登录
     * @param loginacct
     * @param userpswd
     * @return 成功返回用户，否则返回空
     */
    TMember login(String loginacct, String userpswd);

    /**
     * 注册
     * @param member，成功后与否均返回自身，方便表单回显
     * @return
     */
    TMember regist(TMember member);
}
