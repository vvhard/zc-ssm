package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TProjectTemp;
import zc.manager.dao.TProjectTempMapper;
import zc.manager.service.ProjectTempService;

import java.util.List;
import java.util.Map;

@Service
public class ProjectTempServiceImpl implements ProjectTempService {
    @Autowired
    private TProjectTempMapper projectTempMapper;

    @Override
    public void addPayInfo(int project_temp_id, String app_id, String app_private_key, String alipay_public_key) {
        projectTempMapper.insertPayInfo(project_temp_id,app_id,app_private_key,alipay_public_key);
        projectTempMapper.updateStatus(project_temp_id,"CHECKING",null);
    }

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

    @Override
    public List<TProjectTemp> queryData(Map<String, Object> map) {
        return projectTempMapper.selectByMap(map);
    }

    @Override
    public int queryProjectCount() {
        return projectTempMapper.selectProjectCount();
    }

    @Override
    public void checking(int project_temp_id) {
        projectTempMapper.updateStatus(project_temp_id,"CHECKING",null);
    }

    @Override
    public void checkFail(int projectid, String feedback) {
        projectTempMapper.updateStatus(projectid,"CHECKING",feedback);
    }
}
