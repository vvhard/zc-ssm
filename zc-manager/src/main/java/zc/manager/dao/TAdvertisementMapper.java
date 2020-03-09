package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import zc.commons.pojo.TAdvertisement;

import java.util.List;
import java.util.Map;

@Repository
public interface TAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdvertisement record);

    TAdvertisement selectByPrimaryKey(Integer id);

    List<TAdvertisement> selectAll();

    int updateByPrimaryKey(TAdvertisement record);

    List<TAdvertisement> selectWithCondition(Map<String, Object> map);

    int selectCountWithCondition(Map<String, Object> map);

    int updateStatusById(@Param("id") int id, @Param("status")String status);

    List<TAdvertisement> selectTakeOnAdv();
}