package zc.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.api.service.MemberService;
import zc.manager.dao.TMemberMapper;
import zc.commons.pojo.TMember;

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
}
