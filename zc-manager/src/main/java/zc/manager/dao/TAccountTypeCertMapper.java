package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TAccountTypeCert;
import zc.commons.pojo.TAcctType;
import zc.commons.pojo.TCert;

import java.util.List;
@Repository
public interface TAccountTypeCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAccountTypeCert record);

    TAccountTypeCert selectByPrimaryKey(Integer id);

    List<TAccountTypeCert> selectAll();

    int updateByPrimaryKey(TAccountTypeCert record);

    List<TAcctType> selectAcctType();

    List<TCert> selectAcctCerts(String name);

    void deleteByAcctTypeId(int accttypeid);

    void insertAcct(TAccountTypeCert tact);
}