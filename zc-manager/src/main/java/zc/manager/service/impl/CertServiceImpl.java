package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TCert;
import zc.manager.dao.TCertMapper;
import zc.manager.service.CertService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CertServiceImpl implements CertService {
    @Autowired
    private TCertMapper certMapper;
    @Override
    public List<TCert> getAllCerts() {
        return certMapper.selectAll();
    }

    @Override
    public List<TCert> queryData(Map<String, Object> map) {
        return certMapper.selectWithConditon(map);
    }

    @Override
    public int queryUserNums(Map<String, Object> map) {
        return certMapper.selectCountWithCondition(map);
    }

    @Override
    public void deleteById(int id) {
        certMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatch(int[] certid) {
        List<Integer> list = new ArrayList<>();
        for (Integer e : certid) {
            list.add(e);
        }
        certMapper.deleteBatchByPrimaryKey(list);
    }

    @Override
    public void insertCert(TCert cert) {
        certMapper.insert(cert);
    }

    @Override
    public void updateCert(TCert cert) {
        certMapper.update(cert);
    }
}
