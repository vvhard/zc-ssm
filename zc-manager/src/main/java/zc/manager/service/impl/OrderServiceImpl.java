package zc.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.commons.bean.MyOrder;
import zc.commons.bean.MySupport;
import zc.commons.pojo.TOrder;
import zc.manager.dao.TOrderMapper;
import zc.manager.dao.TProjectMapper;
import zc.manager.service.OrderService;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TOrderMapper orderMapper;
    @Autowired
    private TProjectMapper projectMapper;
    @Override
    public List<MySupport> getMySupports(Map<String, Object> map) {
        List<MySupport> supports = orderMapper.selectByMap(map);
        return supports;
    }

    @Override
    public int getMySupportCount(Map<String, Object> map) {
        return orderMapper.selectCountByMap(map);
    }

    @Override
    public MyOrder getOrderByOrderNum(String ordernum) {
        return orderMapper.selectByOrderNum(ordernum);
    }

    @Override
    public MyOrder delOrderByOrderNum(String ordernum) {
        return orderMapper.deleteByOrderNum(ordernum);
    }

    @Override
    public void addOrder(TOrder order) {
        orderMapper.insert(order);
    }
}
