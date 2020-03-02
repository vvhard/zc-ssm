package zc.manager.service;

import zc.commons.pojo.TStartProjectTask;

public interface TaskService {
    int createTask(int projectTempId);

    int addWithReturnIdAndProjectTempId(int projectTempId, int returnTempId);
}
