package com.example.weichao13.demo.mvp.view;

/**
 *
 *  V 定义数据请求后的回调接口，以此来让V做些渲染或者页面跳转等工作
 *
 * Created by weichao13 on 2017/1/10.
 */

public interface MainView {

    void onShowString(String json);

    void jumpNextActivity(long orderId);
}
