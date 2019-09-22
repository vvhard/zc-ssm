package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMemberCert;

import java.util.List;
@Repository
public interface TMemberCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemberCert record);

    TMemberCert selectByPrimaryKey(Integer id);

    List<TMemberCert> selectAll();

    int updateByPrimaryKey(TMemberCert record);
}