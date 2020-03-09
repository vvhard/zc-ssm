package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.bean.MyOrder;
import zc.commons.bean.MySupport;
import zc.commons.pojo.TOrder;

import java.util.List;
import java.util.Map;

@Repository
public interface TOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrder record);

    TOrder selectByPrimaryKey(Integer id);

    List<TOrder> selectAll();

    int updateByPrimaryKey(TOrder record);

    List<MySupport> selectByMap(Map<String, Object> map);

    int selectCountByMap(Map<String, Object> map);

    MyOrder selectByOrderNum(String ordernum);

    MyOrder deleteByOrderNum(String ordernum);
}