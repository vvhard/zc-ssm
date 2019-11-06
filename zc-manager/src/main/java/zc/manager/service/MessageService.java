package zc.manager.service;

import zc.commons.pojo.TMessage;

import java.util.List;
import java.util.Map;

public interface MessageService {
    List<TMessage> queryWithCondition(Map<String, Object> map);

    int queryNums(Map<String, Object> map);
}
