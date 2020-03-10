package zc.manager.service;

import zc.commons.pojo.TMember;
import zc.commons.pojo.TMemberAddress;
import zc.commons.pojo.TMemberCert;
import zc.commons.pojo.TUser;

import java.util.List;
import java.util.Map;

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

    boolean isAuth(String loginacct);

    void submitAuthInfoWithLoginacct(String loginacct, String realname, String tel, String cardnum, String accttype);

    void updateMemberCert(int memberId, List<Integer> certid, List<String> files);

    List<TMember> queryData(Map<String, Object> map);

    int queryMemberCount();

    List<TMemberCert> getCertByMemberId(int memberid);

    List<String> getCertNameByMemberId(int memberid);

    void passAuth(int memberid);

    void failAuth(int memberid, String feedback);

    TMember getByMemberId(int id);

    List<TMemberAddress> getAddress(int memberid);

    void addAddress(TMemberAddress memberAddress);

    TMemberAddress getAddressByAddressId(int addressid);

    boolean updatePassword(String loginacct, String oldPassword, String newPassword);

    void delteAddress(int addressid);
}
