package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMemberAddress;

import java.util.List;
@Repository
public interface TMemberAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemberAddress record);

    TMemberAddress selectByPrimaryKey(Integer id);

    List<TMemberAddress> selectAll();

    int updateByPrimaryKey(TMemberAddress record);
}