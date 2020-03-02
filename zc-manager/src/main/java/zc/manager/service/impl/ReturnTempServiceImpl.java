package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.pojo.TReturnTemp;
import zc.manager.dao.TReturnTempMapper;
import zc.manager.service.ReturnTempService;
@Service
public class ReturnTempServiceImpl implements ReturnTempService {
    @Autowired
    private TReturnTempMapper  returnTempMapper;
    @Override
    public int add(TReturnTemp returnTemp) {
        return returnTempMapper.insert(returnTemp);
    }
}
