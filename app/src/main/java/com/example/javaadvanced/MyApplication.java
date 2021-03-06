package com.example.javaadvanced;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/*import com.example.javaadvanced.OpenSourceFramework.ioc_compile.dagger2.DaggerMyComponent;
import com.example.javaadvanced.OpenSourceFramework.ioc_compile.dagger2.DatabaseModule;
import com.example.javaadvanced.OpenSourceFramework.ioc_compile.dagger2.HttpModule;*/
import com.example.javaadvanced.OpenSourceFramework.ioc_compile.dagger2.DaggerMyComponent;
import com.example.javaadvanced.OpenSourceFramework.ioc_compile.dagger2.DatabaseModule;
import com.example.javaadvanced.OpenSourceFramework.ioc_compile.dagger2.HttpModule;
import com.example.javaadvanced.OpenSourceFramework.ioc_compile.dagger2.MyComponent;
import com.example.javaadvanced.OpenSourceFramework.ioc_compile.dagger2.di.presenter_id.DaggerPresenterComponent;
import com.example.javaadvanced.jvm.AndroidVM.Hotfix;
import com.example.javaadvanced.performanceOptimization.CodeStructDesignPattern.proxymodel.proxy.HttpProxy;
import com.example.javaadvanced.performanceOptimization.CodeStructDesignPattern.proxymodel.proxy.OkHttpModel;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;

public class MyApplication extends Application {
    private String TAG = "MyApplication";

    private MyComponent myComponent;

    public MyComponent getAppComponent() {
        return myComponent;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //执行热修复。 插入补丁包dex。冷启动修复。
        // /data/data/xxx/files/xxxx.dex
        // /sdcard/xxx.dex
        // 修复后的类必须在原来的类还没有被加载时进行加载(双亲委托机制，不然加载的还是原来的类)，
        // 所以补丁包在attachBaseContext()方法里进行安装
        Hotfix.installPatch(this,new File("/sdcard/patch.dex"));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate()");

        //HttpProxy.init(VolleyModel.getInstance(getApplicationContext()));
        //当需要将网络请求库由Volley更换为OkHttp时只需要调用HttpProxy.init()设置新的realObject即可
        HttpProxy.init(new OkHttpModel(getApplicationContext()));

        //LeakCanary
        if(LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        //dragger
        myComponent = DaggerMyComponent.builder()
                .httpModule(new HttpModule())
                .databaseModule(new DatabaseModule())
                .presenterComponent(DaggerPresenterComponent.create())
                .build();

    }

}
