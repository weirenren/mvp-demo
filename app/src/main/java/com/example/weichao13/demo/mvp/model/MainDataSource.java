package com.example.weichao13.demo.mvp.model;

/**
 *
 * M层负责各种io操作，包含网络、数据库等等
 *
 * Created by weichao13 on 2017/1/10.
 */

public interface MainDataSource {

    String getStringFromRemote();
    String getStringFromCache();

}
