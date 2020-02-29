package zc.manager.service;

import zc.commons.pojo.TType;

import java.util.List;
import java.util.Map;

public interface TypeService {
    int queryNums(Map<String, Object> map);

    List<TType> queryData(Map<String, Object> map);

    void addType(TType type);

    void updateType(TType type);

    void delType(Integer typeid);

    void delTypeBatch(Integer[] typeid);

    List<TType> getTypes();
}
