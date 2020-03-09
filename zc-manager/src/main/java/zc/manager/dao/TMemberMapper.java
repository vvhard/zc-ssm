package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TMemberAddress;
import zc.commons.pojo.TMemberCert;

import java.util.List;
import java.util.Map;

@Repository
public interface TMemberMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 注册用，不插入id字段，数据库生成后返回给对象
     * @param record
     * @return
     */
    int insert(TMember record);

    TMember selectByPrimaryKey(Integer id);

    List<TMember> selectAll();

    int updateByPrimaryKey(TMember record);

    TMember selectByLoginacct(@Param("loginacct") String loginacct, @Param("userpswd") String userpswd);

    int selectId(String loginacct);

    void updateByLoginacct(TMember member);

    String selectAuthStatusByAcct(String loginacct);

    void updateAuthInfoByLoginacct(@Param("loginacct")String loginacct, @Param("realname")String realname,
                                   @Param("tel") String tel, @Param("cardnum")String cardnum,
                                   @Param("accttype")String accttype);

    void insert2MemberCertByMemberId(List<TMemberCert> cl);

    List<TMember> selectInChecking(Map<String, Object> map);

    int selectInCheckingCount();

    List<TMemberCert> selectCertByMemberId(int memberid);

    List<String> selectCertNameByMemberId(int memberid);

    int updateAuthStatus(@Param("id") int memberid, @Param("status") String status, @Param("feedback") String feedback);

    TMember selectById(int id);

    List<TMemberAddress> selectAddress(int memberid);

    int updatePassword(@Param("loginacct") String loginacct,
                           @Param("oldPassword")String oldPassword,
                           @Param("newPassword")String newPassword);
}