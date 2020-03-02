package zc.manager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import zc.commons.pojo.TStartProjectTask;

public interface TStartProjectTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TStartProjectTask record);

    TStartProjectTask selectByPrimaryKey(Integer id);

    List<TStartProjectTask> selectAll();

    int updateByPrimaryKey(TStartProjectTask record);

    int insertTask(@Param("project_temp_id") int project_temp_id);

    int addWithReturnIdAndProjectTempId(@Param("project_temp_id") int pid, @Param("return_temp_id") int rid);

    TStartProjectTask selectByProjectId(int project_temp_id);

}