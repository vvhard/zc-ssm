package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TMemberAddress;
import zc.commons.pojo.TUser;
import zc.manager.dao.TMemberAddressMapper;
import zc.manager.service.MemberService;
import zc.commons.pojo.TMemberCert;
import zc.manager.dao.TMemberMapper;
import zc.commons.pojo.TMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {
    /**
     * 要配置了跟mybatis整合才能进行自动注入
     */
    @Autowired
    private TMemberMapper memeberMapper;
    @Autowired
    private TMemberAddressMapper memberAddressMapper;
    /**
     * 登录
     *
     * @param loginacct
     * @param userpswd
     * @return 成功返回用户，否则返回空
     */
    @Override
    public TMember login(String loginacct, String userpswd) {
        TMember memeber = memeberMapper.selectByLoginacct(loginacct, userpswd);
        return memeber == null? null:memeber;
    }

    /**
     * 注册
     * @param member ，成功后与否均返回自身
     * @return
     */
    @Override
    public TMember regist(TMember member) {
        memeberMapper.insert(member);
        return member;
    }

    @Override
    public Integer queryId(String loginacct) {
        return memeberMapper.selectId(loginacct);
    }

    /**
     * 更新字段
     *
     * @param member
     */
    @Override
    public void update(TMember member) {
        memeberMapper.updateByLoginacct(member);
    }


    @Override
    public boolean isAuth(String loginacct) {
        String status = memeberMapper.selectAuthStatusByAcct(loginacct);
        return "Y".equals(status);
    }

    @Override
    public void submitAuthInfoWithLoginacct(String loginacct, String realname, String tel, String cardnum, String accttype) {
        memeberMapper.updateAuthInfoByLoginacct(loginacct,realname,tel,cardnum,accttype);
    }

    @Override
    public void updateMemberCert(int memberId, List<Integer> certid, List<String> files) {
        List<TMemberCert> cl = new ArrayList<>();
        for(int i = 0; i< certid.size();i++){
            TMemberCert tmc = new TMemberCert();
            tmc.setCertid(certid.get(i));
            tmc.setMemberid(memberId);
            tmc.setPath(files.get(i));
            cl.add(tmc);
        }
        memeberMapper.insert2MemberCertByMemberId(cl);
    }

    @Override
    public List<TMember> queryData(Map<String, Object> map) {
        return memeberMapper.selectInChecking(map);
    }

    @Override
    public int queryMemberCount() {
        return memeberMapper.selectInCheckingCount();
    }

    @Override
    public List<TMemberCert> getCertByMemberId(int memberid) {
        return memeberMapper.selectCertByMemberId(memberid);
    }

    @Override
    public List<String> getCertNameByMemberId(int memberid) {
        return memeberMapper.selectCertNameByMemberId(memberid);
    }

    @Override
    public void passAuth(int memberid) {
        memeberMapper.updateAuthStatus(memberid,"Y",null);
    }

    @Override
    public void failAuth(int memberid, String feedback) {
        memeberMapper.updateAuthStatus(memberid,"N",feedback);
    }

    @Override
    public TMember getByMemberId(int id) {
        return memeberMapper.selectById(id);
    }

    @Override
    public List<TMemberAddress> getAddress(int memberid) {
        return memeberMapper.selectAddress(memberid);
    }

    @Override
    public void addAddress(TMemberAddress memberAddress) {
        memberAddressMapper.insert(memberAddress);
    }

    @Override
    public TMemberAddress getAddressByAddressId(int addressid) {
        return memberAddressMapper.selectByPrimaryKey(addressid);
    }

    @Override
    public boolean updatePassword(String loginacct, String oldPassword, String newPassword) {
        return (memeberMapper.updatePassword(loginacct,oldPassword,newPassword) > 0);
    }
}
