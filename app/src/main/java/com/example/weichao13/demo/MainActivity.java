package com.example.weichao13.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.Presenter;
import com.example.mvp.mvp.presenter.MainPresenter;
import com.example.mvp.mvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果Activity/Fragment 页面负责，可以分拆成两组 P/V
 * Created by weichao13 on 2017/1/10.
 */

public class MainActivity extends BaseActivity implements MainView {

    MainPresenter mMainPresenter;

    TextView mShowTxt;

    Button mNextBtn;

    @Override
    public List<Presenter> registerPresenters() {

        mMainPresenter = new MainPresenter(this);

        ArrayList list = new ArrayList();
        list.add(mMainPresenter);

        return list;
    }


    @Override
    public void initArguments(Bundle arguments) {

        // 初始化页面时，需要先发一个请求然后渲染页面
        mMainPresenter.getString();
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mNextBtn = (Button) findViewById(R.id.btn);
    }

    @Override
    public void initActions() {

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // NextActivity是订单详情页
                // 跳转到下一页：需要先请求得到订单id,传给下一页订单详情页，因为订单详情页需要根据订单号请求网络获取订单详情信息
                mMainPresenter.jumpNextActivity();
            }
        });

    }

    @Override
    public int getContentLayoutRes() {
        return android.R.layout.activity_list_item;
    }

    @Override
    public List<Presenter> getPresenters() {
        ArrayList list = new ArrayList();
        list.add(mMainPresenter);
        return list;
    }


    @Override
    public void onShowString(String json) {
        mShowTxt.setText(json);
    }

    @Override
    public void jumpNextActivity(long orderId) {

        NextActivity.launchActivity(getContext(),orderId);
    }

}
