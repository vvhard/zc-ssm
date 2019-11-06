package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TMessage;
import zc.manager.dao.TMessageMapper;
import zc.manager.service.MessageService;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private TMessageMapper messageMapper;
    @Override
    public List<TMessage> queryWithCondition(Map<String, Object> map) {
        return messageMapper.selectWithCondition(map);
    }

    @Override
    public int queryNums(Map<String, Object> map) {
        return messageMapper.selectCountWithCondition(map);
    }
}
