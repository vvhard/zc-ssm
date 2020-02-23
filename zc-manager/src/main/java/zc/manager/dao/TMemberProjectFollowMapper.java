package zc.manager.dao;

import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMemberProjectFollow;

import java.util.List;
@Repository
public interface TMemberProjectFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMemberProjectFollow record);

    TMemberProjectFollow selectByPrimaryKey(Integer id);

    List<TMemberProjectFollow> selectAll();

    int updateByPrimaryKey(TMemberProjectFollow record);
}