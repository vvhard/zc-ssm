package zc.manager.service;

import zc.commons.pojo.TProjectTemp;

import java.util.List;
import java.util.Map;

public interface ProjectTempService {
    /**
     * 新增
     * @param projectTemp
     * @return
     */
    int insert(TProjectTemp projectTemp);

    void addPayInfo(int project_temp_id, String app_id, String app_private_key, String alipay_public_key);

    List<TProjectTemp> queryData(Map<String, Object> map);

    int queryProjectCount();

    void checking(int project_temp_id);

    void checkFail(int projectid, String feedback);
}
