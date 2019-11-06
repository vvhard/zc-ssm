package zc.manager.service;

import zc.commons.pojo.TType;

import java.util.List;
import java.util.Map;

public interface TypeService {
    int queryNums(Map<String, Object> map);

    List<TType> queryData(Map<String, Object> map);
}
