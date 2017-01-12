package com.example.weichao13.demo.base;

import android.os.Bundle;

/**
 * View 生命周期的回调
 *
 * Created by weichao13 on 2017/1/11.
 */

public interface IViewLifeCallback {

    /**
     * 当View可见的回调，访问网络都在这个时间之后，为保证
     */
    void onViewCreated(Bundle savedInstance);

    /**
     * 同Activity 和Fragment 的onStart
     */
    void onViewStart();

    /**
     * 同Activity 和Fragment 的onResume
     */
    void onViewResume();

    /**
     * 同Activity 和Fragment 的onPause
     */
    void onViewPause();


    /**
     * 同Activity 和Fragment 的onStop
     */
    void onViewStop();

    /**
     * Fragment Activity 都有的方法
     *
     * @param outState
     */
    void onSaveInstanceState(Bundle outState);

    void onViewDestroy();

}
