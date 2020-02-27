package zc.api.service;

import zc.commons.pojo.TMember;
import zc.commons.pojo.TMemberCert;

import java.util.List;

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

    Integer queryId(String loginacct);

    /**
     * 更新字段
     * @param member
     */
    void update(TMember member);

    /**
     * 保存带t_member_cert,实名认证时的资质图片信息
     * @param list
     */
    void cert(List<TMemberCert> list);

    void auth(TMember member, List<TMemberCert> list);
}
