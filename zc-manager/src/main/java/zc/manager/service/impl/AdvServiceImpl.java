package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TAdvertisement;
import zc.manager.dao.TAdvertisementMapper;
import zc.manager.service.AdvService;

import java.util.List;
import java.util.Map;

@Service
public class AdvServiceImpl implements AdvService {
    @Autowired
    private TAdvertisementMapper advertisementMapper;
    @Override
    public List<TAdvertisement> queryData(Map<String, Object> map) {
        return advertisementMapper.selectWithCondition(map);
    }

    @Override
    public int queryUserNums(Map<String, Object> map) {
        return advertisementMapper.selectCountWithCondition(map);
    }

    @Override
    public TAdvertisement getDetailById(int id) {
        return advertisementMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addAdv(TAdvertisement advertisement) {
        advertisementMapper.insert(advertisement);
    }

    @Override
    public int deleteAdvById(int id) {
        return advertisementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int changAdvStatus(int id, String status) {
        return  advertisementMapper.updateStatusById(id,status);
    }

    @Override
    public List<TAdvertisement> getShowAdv() {
        return advertisementMapper.selectTakeOnAdv();
    }
}
