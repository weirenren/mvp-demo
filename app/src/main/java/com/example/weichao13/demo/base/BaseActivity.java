package com.example.weichao13.demo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Activity 基类
 * 提供对Presenter的生命周期的管理
 *
 * Created by weichao13 on 2017/1/10.
 */

public abstract class BaseActivity extends Activity implements IPage {

    private Set<IViewLifeCallback> mCallbacks = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = getContentLayoutRes();

        if(layoutId ==0 ){
            throw new IllegalArgumentException("Resource id is 0");
        }

        initPresenter();

        initArguments(savedInstanceState);

        setContentView(layoutId);

        initView(savedInstanceState);

        initActions();


        for (IViewLifeCallback iViewLifeCallback : mCallbacks) {
            iViewLifeCallback.onViewCreated(savedInstanceState);
        }

    }

    void initPresenter(){
        List<Presenter> presenters = registerPresenters();
        if (presenters != null) {
            for (Presenter presenter : presenters) {
                registerLifeCircleListener(presenter);
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        for (IViewLifeCallback iViewLifeCallback : mCallbacks) {
            iViewLifeCallback.onViewStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (IViewLifeCallback iViewLifeCallback : mCallbacks) {
            iViewLifeCallback.onViewResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (IViewLifeCallback iViewLifeCallback : mCallbacks) {
            iViewLifeCallback.onViewPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        for (IViewLifeCallback iViewLifeCallback : mCallbacks) {
            iViewLifeCallback.onViewStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (IViewLifeCallback iViewLifeCallback : mCallbacks) {
            iViewLifeCallback.onViewDestroy();
        }

        List<Presenter> presenters = getPresenters();
        for (Presenter presenter : presenters) {
            unregisterLifeCircleListener(presenter);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (IViewLifeCallback iViewLifeCallback : mCallbacks) {
            iViewLifeCallback.onSaveInstanceState(outState);
        }
    }

    @Override
    public void registerLifeCircleListener(IViewLifeCallback callback) {
        if (callback == null) {
            return;
        }

        mCallbacks.add(callback);
    }

    @Override
    public void unregisterLifeCircleListener(IViewLifeCallback callback) {
        if (callback == null) {
            return;
        }

        mCallbacks.remove(callback);
    }

    @Override
    public Context getContext() {
        return getContext();
    }


}
