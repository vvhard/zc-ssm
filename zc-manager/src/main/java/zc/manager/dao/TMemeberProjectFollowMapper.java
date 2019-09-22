package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMemeberProjectFollow;

import java.util.List;
@Repository
public interface TMemeberProjectFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemeberProjectFollow record);

    TMemeberProjectFollow selectByPrimaryKey(Integer id);

    List<TMemeberProjectFollow> selectAll();

    int updateByPrimaryKey(TMemeberProjectFollow record);
}