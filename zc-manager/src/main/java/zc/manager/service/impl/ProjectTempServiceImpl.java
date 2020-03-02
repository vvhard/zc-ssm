package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TProjectTemp;
import zc.manager.dao.TProjectTempMapper;
import zc.manager.service.ProjectTempService;

@Service
public class ProjectTempServiceImpl implements ProjectTempService {
    @Autowired
    private TProjectTempMapper projectTempMapper;
    /**
     * 新增
     *
     * @param projectTemp
     * @return
     */
    @Override
    public int insert(TProjectTemp projectTemp) {
        return projectTempMapper.insertProjectTemp(projectTemp);
    }
}
