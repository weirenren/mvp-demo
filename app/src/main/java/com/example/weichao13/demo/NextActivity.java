package com.example.weichao13.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.Presenter;
import com.example.mvp.mvp.view.NextView;

import java.util.List;

/**
 * Created by weichao13 on 2017/1/11.
 */

public class NextActivity extends BaseActivity implements NextView{

    public static final String KEY_ORDERID = "key_orderId";

    public static void launchActivity(Context context, final long orderId){

        Intent intent = new Intent(context, NextActivity.class);
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putLong(NextActivity.KEY_ORDERID, orderId);

        intent.putExtras(bundle);

        context.startActivity(intent);
    }


    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initActions() {

    }

    @Override
    public List<Presenter> registerPresenters() {
        return null;
    }

    @Override
    public void initArguments(Bundle arguments) {

    }

    @Override
    public int getContentLayoutRes() {
        return 0;
    }

    @Override
    public List<Presenter> getPresenters() {
        return null;
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailure() {

    }
}
