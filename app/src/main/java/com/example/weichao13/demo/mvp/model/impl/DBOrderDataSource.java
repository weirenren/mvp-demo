package com.example.weichao13.demo.mvp.model.impl;

import com.example.mvp.mvp.model.OrderDataSource;

/**
 * Created by weichao13 on 2017/1/11.
 */

public class DBOrderDataSource implements OrderDataSource {
    @Override
    public long getOrderId() {

        // 数据库获取订单id
        return 0;
    }
}
