package com.example.weichao13.demo.base;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

/**
 * 抽象页面的接口，统一业务开发
 *
 * Created by weichao13 on 2017/1/11.
 */

public interface IPage {

    /**
     * 初始化页面参数
     */
    void initArguments(Bundle arguments);

    /**
     * 规范方法，初始化View
     */
    void initView(Bundle savedInstanceState);

    /**
     * 规范方法, 初始化Action
     */
    void initActions();

    /**
     * 初始化 Presenter
     */
    List<Presenter> registerPresenters();

    /**
     * 规范方法，初始化layoutId
     * @return
     */
    int getContentLayoutRes();

    /**
     * 注册生命周期监听
     *
     * @param callback
     */
    void registerLifeCircleListener(IViewLifeCallback callback);

    /**
     * 解注册生命周期监听
     *
     * @param callback
     */
    void unregisterLifeCircleListener(IViewLifeCallback callback);

    /**
     * 拿到Activity Context
     *
     * @return
     */
    Context getContext();

    /**
     * 获取初始化的Presenter
     * @return
     */
    List<Presenter> getPresenters();

    void finish();

}
