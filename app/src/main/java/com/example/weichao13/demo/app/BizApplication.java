package com.example.weichao13.demo.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by chaowei on 17/1/11.
 */

public class BizApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        BizServiceContext.getInstance().init(base);


        SettingStore singleInstance = BizServiceContext.getInstance().getService(SettingStore.class);

    }
}
