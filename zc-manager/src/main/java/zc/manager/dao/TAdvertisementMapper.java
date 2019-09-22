package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TAdvertisement;

import java.util.List;
@Repository
public interface TAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdvertisement record);

    TAdvertisement selectByPrimaryKey(Integer id);

    List<TAdvertisement> selectAll();

    int updateByPrimaryKey(TAdvertisement record);
}