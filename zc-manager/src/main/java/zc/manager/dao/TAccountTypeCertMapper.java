package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TAccountTypeCert;

import java.util.List;
@Repository
public interface TAccountTypeCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAccountTypeCert record);

    TAccountTypeCert selectByPrimaryKey(Integer id);

    List<TAccountTypeCert> selectAll();

    int updateByPrimaryKey(TAccountTypeCert record);
}