package com.bawei.zhanglei.frescodemo.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class AppFresco extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
