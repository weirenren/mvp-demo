package com.example.weichao13.demo.mvp.presenter;

import com.example.mvp.base.IPage;
import com.example.mvp.base.Presenter;
import com.example.mvp.mvp.model.MainDataSource;
import com.example.mvp.mvp.model.OrderDataSource;
import com.example.mvp.mvp.model.impl.NetOrderDataSource;
import com.example.mvp.mvp.model.impl.TaskDataSourceImpl;
import com.example.mvp.mvp.model.test.TaskDataSourceTestImpl;
import com.example.mvp.mvp.view.MainView;

/**
 * P 用来桥接V和M，提供业务方法，做简单的请求响应转接业务，
 * Created by weichao13 on 2017/1/10.
 */

public class MainPresenter extends Presenter<MainView> {

    private MainDataSource mMainDataSource;
    private OrderDataSource mOrderDataSource;

    public MainPresenter(IPage view){
        super(view);
        this.mView = createProxyDelegate(MainView.class,(MainView) view ,this);

        this.mMainDataSource = new TaskDataSourceImpl();
        this.mOrderDataSource = new NetOrderDataSource();
    }

    public MainPresenter test(){
        this.mMainDataSource = new TaskDataSourceTestImpl();
        return this;
    }

    public void getString() {
        String str = mMainDataSource.getStringFromCache() + mMainDataSource.getStringFromRemote();
        mView.onShowString(str);
    }

    public void jumpNextActivity(){

        final long orderId = mOrderDataSource.getOrderId();
        mView.jumpNextActivity(orderId);

    }



}
