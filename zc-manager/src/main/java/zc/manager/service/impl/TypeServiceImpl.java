package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TType;
import zc.manager.dao.TTypeMapper;
import zc.manager.service.TypeService;

import java.util.List;
import java.util.Map;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TTypeMapper typeMapper;
    @Override
    public int queryNums(Map<String, Object> map) {
        return typeMapper.selectCountWithCondition(map);
    }

    @Override
    public List<TType> queryData(Map<String, Object> map) {
        return typeMapper.selectWithCondition(map);
    }

    @Override
    public void addType(TType type) {
        typeMapper.insert(type);
    }

    @Override
    public void updateType(TType type) {
        typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public void delType(Integer typeid) {
        typeMapper.deleteByPrimaryKey(typeid);
    }

    @Override
    public void delTypeBatch(Integer[] typeid) {
        typeMapper.delBatch(typeid);
    }
}
