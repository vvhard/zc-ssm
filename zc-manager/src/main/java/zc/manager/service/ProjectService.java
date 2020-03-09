package zc.manager.service;

import zc.commons.bean.MyFollow;
import zc.commons.bean.MyInitiate;
import zc.commons.bean.MyProject;
import zc.commons.bean.ProjectPayInfo;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;
import zc.commons.pojo.TReturn;

import java.util.List;

public interface ProjectService {
    /**
     * 获取推荐项目，暂时先将前三个当成是
     * @return
     */
    List<TProject> getRecommand();

    /**
     * 根据分类获取项目
     * @param project_type
     * @return
     */
    List<TProject> getProject(String project_type);

    /**
     * 获取所有项目
     * @param project_type
     * @return
     */
    List<TProject> getProjects(String project_type);

    List<TProject> getProjects();

    MyProject getProjectById(int id);

    /**
     * 获取项目发布者信息，根据memberid
     * @param id
     * @return
     */
    TMember getProjectMemById(int id);

    List<TProject> getProjectByType(String type);

    /**
     * 根据条件进行查询
     * @param start
     * @param pagesize
     * @param type
     * @param status
     * @param order
     * @param queryContent
     * @return
     */
    List<TProject> getProjects(int start,int pagesize,String type,String status, String order,String queryContent);

    /**
     * 获取查询条件下的项目总数
     * @param start
     * @param pagesize
     * @param type
     * @param status
     * @param order
     * @param queryContent
     * @return
     */
    int getProjectCount(int start, int pagesize, String type, String status, String order, String queryContent);

    List<TProject> getIndexProject(String project_type);

    void addProjectByTempProject(int projectTempId);

    List<MyFollow> getFollowProject(int memberid, int strat, int size);

    int getFollowProjectCount(int memberid);

    List<MyInitiate> getInitiateProject(int memberid, int start, int pagesize, String status);

    int getInitiateProjectCount(int memberid, String status);

    void cancelFollow(int memberid, int projectid);

    void followProject(int memberid, int projectid);

    boolean queryFollowStatus(int memberid, int projectid);

    List<TReturn> getReturnByProjectId(int projectid);

    TReturn getReturnByReturnId(int returnid);

    ProjectPayInfo getProjectPayInfo(int projectid);
}
