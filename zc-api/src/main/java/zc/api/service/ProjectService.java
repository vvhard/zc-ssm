package zc.api.service;

import zc.commons.pojo.TMember;
import zc.commons.pojo.TProject;

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

    TProject getProjectById(int id);

    /**
     * 获取项目发布者信息，根据memberid
     * @param id
     * @return
     */
    TMember getProjectMemById(int id);
}
