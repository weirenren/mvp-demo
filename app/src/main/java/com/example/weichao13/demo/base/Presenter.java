package com.example.weichao13.demo.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 *
 * Created by weichao13 on 2017/1/11.
 */

public abstract class Presenter<V> implements IViewLifeCallback {

    private static final boolean DEBUG_LIFE = true;

    private String mTag = this.getClass().getSimpleName();

    protected Context mContext;
    protected V mView;

    private boolean mIsViewLive = false;


    /**
     * @param view presenter控制的View
     */
    public Presenter(IPage view) {
        this.mContext = view.getContext();
    }

    protected Context getContext() {
        return mContext;
    }

    @Override
    public void onViewCreated(Bundle savedInstance) {
        mIsViewLive = true;
        if (DEBUG_LIFE) {
            Log.d(mTag, "Life:[onViewCreated]");
        }
    }

    @Override
    public void onViewStart() {
        if (DEBUG_LIFE) {
            Log.d(mTag, "Life:[onViewStart]");
        }
    }

    @Override
    public void onViewResume() {
        if (DEBUG_LIFE) {
            Log.d(mTag, "Life:[onViewResume]");
        }
    }

    @Override
    public void onViewPause() {
        if (DEBUG_LIFE) {
            Log.d(mTag, "Life:[onViewPause]");
        }
    }

    @Override
    public void onViewStop() {
        if (DEBUG_LIFE) {
            Log.d(mTag, "Life:[onViewStop]");
        }
    }

    /**
     * 解开注册
     */
    @Override
    public void onViewDestroy() {
        mIsViewLive = false;
        if (DEBUG_LIFE) {
            Log.d(mTag, "Life:[onViewDestroy]");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /**
     * 活着的时候能调用
     *
     * @return
     */
    public boolean isViewLive() {
        return mIsViewLive;
    }

    /**
     * Delegate Dynamic Proxy ,动态代理所有Delegate
     */
    protected static class DelegateHandler implements InvocationHandler {
        private Object mProxied;
        private Presenter mPresenter;

        public DelegateHandler(Object proxied, Presenter presenter) {
            this.mProxied = proxied;
            this.mPresenter = presenter;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //
            try {
                if (DEBUG_LIFE) {
                    Log.d(mPresenter.mTag, "Invoke Delegate Method");
                }
                if (mPresenter.isViewLive()) {
                    return method.invoke(mProxied, args);
                } else {
                    if (DEBUG_LIFE) {
                        Log.d(mPresenter.mTag, "Not have to invoke delegate method ");
                    }
                    return null;
                }
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    }

    /**
     * 创建一个动态代理,处理Fragment 销毁回调时候的Crash问题,不用再到处判断Fragment isAdded 以及Activity 中常出现的 BadTokenExecption:unable to add window的问题
     *
     * @param clazz
     * @return
     */
    protected static <T> T createProxyDelegate(Class<?> clazz, T real, Presenter presenter) {
        T proxySubject = (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new DelegateHandler(real, presenter));
        return proxySubject;
    }

}
