package com.example.weichao13.demo.app;

import java.util.List;

/**
 * Created by chaowei on 17/1/11.
 */

public final class BizServiceContext extends CommonServiceContext{

    private BizServiceContext() {

    }

    //在访问Application时创建单例
    private static class SingletonHolder {
        private static final BizServiceContext INSTANCE = new BizServiceContext();
    }

    //获取单例
    public static BizServiceContext getInstance() {
        return SingletonHolder.INSTANCE;
    }



    @Override
    protected List<Object> registerService() {
        return null;
    }
}
