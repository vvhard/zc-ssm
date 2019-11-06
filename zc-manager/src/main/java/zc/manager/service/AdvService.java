package zc.manager.service;

import zc.commons.pojo.TAdvertisement;

import java.util.List;
import java.util.Map;

public interface AdvService {
    List<TAdvertisement> queryData(Map<String, Object> map);

    int queryUserNums(Map<String, Object> map);

    TAdvertisement getDetailById(int id);
}
