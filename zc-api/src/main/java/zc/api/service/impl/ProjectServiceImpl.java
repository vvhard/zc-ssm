package zc.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.api.service.ProjectService;
import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;
import zc.manager.dao.TProjectMapper;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private TProjectMapper projectMapper;

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
    public TProject getProjectById(int id) {
        return projectMapper.selectByPrimaryKey(id);
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
}
