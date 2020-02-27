package zc.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zc.api.service.MemberService;
import zc.commons.pojo.TMemberCert;
import zc.manager.dao.TMemberMapper;
import zc.commons.pojo.TMember;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    /**
     * 要配置了跟mybatis整合才能进行自动注入
     */
    @Autowired
    private TMemberMapper memeberMapper;
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

    /**
     * 保存带t_member_cert,实名认证时的资质图片信息
     *
     * @param list
     */
    @Override
    public void cert(List<TMemberCert> list) {
        memeberMapper.insert_cert(list);
    }

    @Override
    @Transactional
    public void auth(TMember member,List<TMemberCert> list){
        memeberMapper.updateByLoginacct(member);
        memeberMapper.insert_cert(list);

    }
}
