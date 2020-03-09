package zc.manager.service;

import zc.commons.bean.MyOrder;
import zc.commons.bean.MySupport;
import zc.commons.pojo.TOrder;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<MySupport> getMySupports(Map<String, Object> map);

    int getMySupportCount(Map<String, Object> map);

    MyOrder getOrderByOrderNum(String ordernum);

    MyOrder delOrderByOrderNum(String ordernum);

    void addOrder(TOrder order);
}
