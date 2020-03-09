package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.pojo.TReturn;
import zc.commons.pojo.TReturnTemp;

import java.util.List;
@Repository
public interface TReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReturn record);

    TReturn selectByPrimaryKey(Integer id);

    List<TReturn> selectAll();

    int updateByPrimaryKey(TReturn record);

    void insertByTempProject(int projectTempId);

    int insertByTempProject(@Param("projectid") int projectid, @Param("rtn") TReturnTemp r);
}