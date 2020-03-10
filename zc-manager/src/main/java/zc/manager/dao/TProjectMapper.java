package zc.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zc.commons.bean.MyFollow;
import zc.commons.bean.MyInitiate;
import zc.commons.bean.MyProject;
import zc.commons.bean.ProjectPayInfo;
import zc.commons.pojo.*;

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

    List<TProject> selectIndexProjects(@Param("type") String type);

    List<TProject> selectProjects(@Param("start") int start, @Param("size") int pagesize,
                                  @Param("type") String type,@Param("status")  String status,
                                  @Param("order") String order,@Param("queryContent")  String queryContent);

    int selectProjectsCount(@Param("start") int start, @Param("size") int pagesize,
                                  @Param("type") String type,@Param("status")  String status,
                                  @Param("order") String order,@Param("queryContent")  String queryContent);

    void insertByTempProject(TProject projectTempId);

    void insertPayInfoByTempProject(@Param("projectid") int projectid, @Param("project") TProjectTemp project);

    void insertTypeByTempProject(@Param("projectid")int projectid, @Param("typeid")int typeid);

    void insertPicPathByTempProject(@Param("projectid")int projectid, @Param("project")TProjectTemp project);

    List<MyFollow> selectFollowProjectByMemberId(@Param("memberid") int memberid, @Param("start")int start,
                                                 @Param("size")int size);

    int selectFollowCountByMemberId(int memberid);

    List<MyInitiate> selectInitiateProjectByMemberId(@Param("memberid") int memberid, @Param("start")int start,
                                                     @Param("size")int size, @Param("status") String status);

    int selectInitiateProjectCountByMemberId(@Param("memberid")int memberid, @Param("status") String status);

    void insertFollow(@Param("memberid")int memberid, @Param("projectid")int projectid);

    void deleteFollow(@Param("memberid")int memberid, @Param("projectid")int projectid);

    MyProject selectToMyProject(@Param("projectid")int projectid);

    TMemberProjectFollow selectFormFollow(@Param("memberid")int memberid, @Param("projectid")int projectid);

    List<TReturn> selectReturnByProjectid(int projectid);

    ProjectPayInfo selectPayInfo(int projectid);

    void updateFollowCount(@Param("projectid") int projectid, @Param("follower") int follower);

    void updateProjectAfterSupport(@Param("projectid")Integer projectid, @Param("money")double money);
}