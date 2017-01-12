package com.example.weichao13.demo.app;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chaowei on 17/1/11.
 */

public abstract class CommonServiceContext {

    protected Context mContext;

    protected Map<Class, Object> services = new HashMap();

    public void init(Context context) {
        this.mContext = context;
        List<Object> registerServices = null;
        try {
            registerServices = registerService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (registerServices != null) {
            for (Object service : registerServices) {
                services.put(service.getClass(), service);
            }
        }
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * 获取服务
     * 这里有个约定，registerService中初始注册的的core service是保证存在的
     * 获取后不必判空,否则通过{@link #getService(Class)}获取服务时需做判空处理
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public final <T extends Object> T getService(Class<T> clazz) {
        T service = (T) services.get(clazz);
        /**
         * 默认构造方法，无须注册直接创建
         */
        if (service == null) {
            synchronized (this) {
                try {
                    service = clazz.newInstance();
                    services.put(clazz, service);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
        return service;
    }

    public final boolean exist(Class clazz) {
        return services.containsKey(clazz);
    }

    public final <T extends Object> T unRegisterService(Class<T> clazz) {

        T service = (T) services.get(clazz);

        if (service != null) {
            synchronized (this) {
                services.remove(service.getClass());
            }
        }
        return service;
    }


    /**
     * 注册基本的service
     *
     * @return 核心service
     */
    protected abstract List<Object> registerService();

    /**
     * 添加新的服务
     *
     * @param service
     */
    public final void addService(Object service) {
        services.put(service.getClass(), service);
    }


    /**
     * 替换当前的服务
     *
     * @param service
     */
    public final void replaceService(Object service) {
        if (services.containsKey(service.getClass())) {
            services.remove(service.getClass());
        }
        services.put(service.getClass(), service);
    }

    public void destory() {
        services.clear();
    }
}
