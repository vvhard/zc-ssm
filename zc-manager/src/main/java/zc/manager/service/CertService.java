package zc.manager.service;

import zc.commons.pojo.TCert;

import java.util.List;
import java.util.Map;

public interface CertService {
    List<TCert> getAllCerts();

    List<TCert> queryData(Map<String, Object> map);

    int queryUserNums(Map<String, Object> map);

    void deleteById(int id);

    void deleteBatch(int[] certid);

    void insertCert(TCert cert);

    void updateCert(TCert cert);
}
