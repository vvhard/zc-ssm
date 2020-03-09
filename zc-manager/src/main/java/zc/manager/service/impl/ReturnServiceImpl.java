package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TReturn;
import zc.manager.dao.TReturnMapper;
import zc.manager.service.ReturnService;
@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private TReturnMapper returnMapper;
    @Override
    public TReturn getReturn(int returnid) {
        return returnMapper.selectByPrimaryKey(returnid);
    }
}
