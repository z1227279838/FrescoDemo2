package com.bawei.zhanglei.frescodemo;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView fresco_demo;
    private Button yuanjiao;
    private Button yuanxing;
    private Button bili;
    private Button jianjin;
    private Button cipan;
    private Button gif;
    private Button listener;
    private Button ok_http;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initData();
    }

    /**
     *初始化视图
     */
    private void initview() {
        //初始化fresco
        fresco_demo = findViewById(R.id.fresco_demo);
        //初始化按钮
        yuanjiao = findViewById(R.id.yuanjiao);
        yuanxing = findViewById(R.id.yuanxing);
        bili = findViewById(R.id.bili);
        jianjin = findViewById(R.id.jianjin);
        cipan = findViewById(R.id.cipan);
        gif = findViewById(R.id.gif);
        listener = findViewById(R.id.listener);
        ok_http = findViewById(R.id.ok_http);
        yuanjiao.setOnClickListener(this);
        yuanxing.setOnClickListener(this);
        bili.setOnClickListener(this);
        jianjin.setOnClickListener(this);
        cipan.setOnClickListener(this);
        gif.setOnClickListener(this);
        listener.setOnClickListener(this);
        ok_http.setOnClickListener(this);
    }

    /**
     *加载图片
      */
    private void initData() {
        uri = Uri.parse("http://img1.imgtn.bdimg.com/it/u=2725262009,4290107754&fm=26&gp=0.jpg");
        fresco_demo.setImageURI(uri);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.yuanjiao:
                RoundingParams radiusRoundingParams = RoundingParams.fromCornersRadius(5f);
                radiusRoundingParams.setRoundAsCircle(false);
                radiusRoundingParams.setCornersRadius(20f);
                fresco_demo.getHierarchy().setRoundingParams(radiusRoundingParams);
                break;
            case R.id.yuanxing:
                RoundingParams circleRoundingParams = RoundingParams.fromCornersRadius(5f);
                circleRoundingParams.setRoundAsCircle(true);
                fresco_demo.getHierarchy().setRoundingParams(circleRoundingParams);
                break;
            case R.id.bili:
                fresco_demo.setAspectRatio(1.2f);
                break;
            case R.id.jianjin:
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                AbstractDraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(fresco_demo.getController())
                        .build();
                fresco_demo.setController(draweeController);
                break;
            case R.id.cipan:
                DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                        .setBaseDirectoryName("img_icon")
                        .setBaseDirectoryPath(Environment.getDataDirectory())
                        .build();
                ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                        .setMainDiskCacheConfig(diskCacheConfig)
                        .build();
                Fresco.initialize(this,config);

                break;
            case R.id.gif:
                AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        .setUri(Uri.parse("http://img.soogif.com/wKPS4WyAImkZ692DUvTsodBXfuKJs6er.gif"))
                        .build();
                fresco_demo.setController(build);
                break;
            case R.id.listener:
                break;
            case  R.id.ok_http:
                break;
        }
    }
}
