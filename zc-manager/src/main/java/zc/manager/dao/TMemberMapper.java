package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMember;

import java.util.List;
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
}