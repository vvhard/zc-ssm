package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zc.commons.bean.MyFollow;
import zc.commons.bean.MyInitiate;
import zc.commons.bean.MyProject;
import zc.commons.bean.ProjectPayInfo;
import zc.commons.pojo.*;
import zc.manager.dao.*;
import zc.manager.service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private TProjectMapper projectMapper;
    @Autowired
    private TProjectTempMapper projectTempMapper;
    @Autowired
    private TReturnMapper returnMapper;
    @Autowired
    private TReturnTempMapper returnTempMapper;
    @Autowired
    private TTypeMapper typeMapper;

    /**
     * 获取推荐项目，暂时先将前三个当成是
     *
     * @return
     */
    @Override
    public List<TProject> getRecommand() {
        // TODO 推荐
        return projectMapper.selectRange(0,3); // 先默认三个
    }

    /**
     * 根据分类获取项目
     *
     * @param project_type
     * @return
     */
    @Override
    public List<TProject> getProject(String project_type) {
        return projectMapper.selectByType(project_type);
    }

    /**
     * 获取所有项目
     *
     * @param project_type
     * @return
     */
    @Override
    public List<TProject> getProjects(String project_type) {
        return projectMapper.selectAll();
    }

    @Override
    public List<TProject> getProjects() {
//        return projectMapper.selectAll();
        // TODO 其他做完后改回来，选择全部
        return projectMapper.selectRange(3,7); // 先默认后四个
    }

    @Override
    public MyProject getProjectById(int id) {
        return projectMapper.selectToMyProject(id);
    }

    /**
     * 获取项目发布者信息，根据memberid
     *
     * @param memberId
     * @return
     */
    @Override
    public TMember getProjectMemById(int memberId) {
        return projectMapper.selectProjectMem(memberId);
    }

    @Override
    public List<TProject> getProjectByType(String type) {
        return projectMapper.selectByType(type);
    }

    /**
     * 根据条件进行查询
     *
     * @param start
     * @param pagesize
     * @param type
     * @param status
     * @param order
     * @param queryContent
     * @return
     */
    @Override
    public List<TProject> getProjects(int start, int pagesize, String type, String status, String order, String queryContent) {
        return projectMapper.selectProjects(start,pagesize,type, status, order, queryContent);
    }

    /**
     * 获取查询条件下的项目总数
     *
     * @param start
     * @param pagesize
     * @param type
     * @param status
     * @param order
     * @param queryContent
     * @return
     */
    @Override
    public int getProjectCount(int start, int pagesize, String type, String status, String order, String queryContent) {
        return projectMapper.selectProjectsCount(start, pagesize, type, status, order, queryContent);
    }
    @Override
    public List<TProject> getIndexProject(String project_type) {
        return projectMapper.selectIndexProjects(project_type);
    }

    @Override
    @Transactional
    public void addProjectByTempProject(int projectTempId) {
        TProjectTemp projectTemp = projectTempMapper.selectByPrimaryKey(projectTempId);
        TProject project = new TProject();
        project.setMemberid(projectTemp.getMemberid());
        project.setName(projectTemp.getName());
        project.setRemark(projectTemp.getRemark());
        project.setDay(projectTemp.getDay());
        project.setMoney(projectTemp.getMoney());
        project.setCreatedate(projectTemp.getCreatedate());
        project.setConcat(projectTemp.getContact());
        projectMapper.insertByTempProject(project); // 设置项目基本信息
        int projectid = project.getId();
        projectMapper.insertPicPathByTempProject(projectid,projectTemp); // 设置项目图片
        projectMapper.insertPayInfoByTempProject(projectid,projectTemp); // 设置项目支付信息
        projectMapper.insertTypeByTempProject(projectid,typeMapper.selectIdByName(projectTemp.getType())); // 设置项目分类信息
        List<TReturnTemp> returnTemp = returnTempMapper.selectByProjectId(projectTempId);
        for(TReturnTemp r:returnTemp){
            returnMapper.insertByTempProject(projectid,r); // 设置回报表
        }
        projectTempMapper.deleteByPrimaryKey(projectTempId); // 删除临时记录
    }

    @Override
    public List<MyFollow> getFollowProject(int memberid, int start, int size) {
        return projectMapper.selectFollowProjectByMemberId(memberid,start,size);
    }

    @Override
    public int getFollowProjectCount(int memberid) {
        return projectMapper.selectFollowCountByMemberId(memberid);
    }

    @Override
    public List<MyInitiate> getInitiateProject(int memberid, int start, int size, String status) {
        return projectMapper.selectInitiateProjectByMemberId(memberid,start,size,status);
    }

    @Override
    public int getInitiateProjectCount(int memberid, String status) {
        return projectMapper.selectInitiateProjectCountByMemberId(memberid,status);
    }

    @Override
    public void cancelFollow(int memberid, int projectid) {
        projectMapper.deleteFollow(memberid,projectid);
    }

    @Override
    public void followProject(int memberid, int projectid) {
        projectMapper.insertFollow(memberid,projectid);
    }

    @Override
    public boolean queryFollowStatus(int memberid, int projectid) {
        return projectMapper.selectFormFollow(memberid,projectid) != null?true:false;
    }

    @Override
    public List<TReturn> getReturnByProjectId(int projectid) {
        return projectMapper.selectReturnByProjectid(projectid);
    }

    @Override
    public TReturn getReturnByReturnId(int returnid) {
        return returnMapper.selectByPrimaryKey(returnid);
    }

    @Override
    public ProjectPayInfo getProjectPayInfo(int projectid) {
        return projectMapper.selectPayInfo(projectid);
    }
}
