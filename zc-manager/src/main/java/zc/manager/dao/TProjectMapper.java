package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;

import java.util.List;
@Repository
public interface TProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProject record);

    TProject selectByPrimaryKey(Integer id);

    List<TProject> selectAll();

    int updateByPrimaryKey(TProject record);

    void selectRecommand();

    List<TProject> selectRange(@Param("from") int from, @Param("to") int to);

    List<TProject> selectByType(String project_type);

    TMember selectProjectMem(int memberId);

    List<TProject> selectProjects(@Param("start") int start, @Param("size") int pagesize,
                                  @Param("type") String type,@Param("status")  String status,
                                  @Param("order") String order,@Param("queryContent")  String queryContent);

    int selectProjectsCount(@Param("start") int start, @Param("size") int pagesize,
                                  @Param("type") String type,@Param("status")  String status,
                                  @Param("order") String order,@Param("queryContent")  String queryContent);

}