package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TStartProjectTask;
import zc.manager.dao.TStartProjectTaskMapper;
import zc.manager.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TStartProjectTaskMapper taskMapper;
    @Override
    public int createTask(int project_temp_id) {
        return taskMapper.insertTask(project_temp_id);
    }

    @Override
    public int addWithReturnIdAndProjectTempId(int projectTempId,int returnTempId) {
        return taskMapper.addWithReturnIdAndProjectTempId(projectTempId,returnTempId);
    }

}
