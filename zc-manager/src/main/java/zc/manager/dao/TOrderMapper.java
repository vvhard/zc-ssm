package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TOrder;

import java.util.List;
@Repository
public interface TOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    List<TOrder> selectAll();

    int updateByPrimaryKey(TOrder record);
}