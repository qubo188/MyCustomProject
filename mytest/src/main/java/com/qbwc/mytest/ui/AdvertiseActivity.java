package com.qbwc.mytest.ui;

import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qbwc.mytest.R;
import com.qbwc.mytest.base.BaseActivity;
import com.qbwc.mytest.config.AppContext;
import com.qbwc.mytest.database.DbService;
import com.qbwc.mytest.entity.Advertise;
import com.qbwc.mytest.ui.widget.CountDown;

import butterknife.Bind;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/15 0015 上午 11:44
 */
public class AdvertiseActivity extends BaseActivity{

    @Bind(R.id.splashId)
    ImageView splashId;


    private SimpleDraweeView adId;

    @Bind(R.id.countDownId)
    TextView countDownId;

    private CountDown countDown;

    private Advertise ad;

    @Override
    public int setLayoutId(){
        return R.layout.activity_advertise;
    }

    @Override
    public boolean availableStatusBar(){
        return false;
    }

    @Override
    public void operation_before_show_ui(){

        ad = DbService.queryLastOne(AppContext.getDaoSession(this).getAdvertiseDao(), "ADVERTISE");

    }
    @Override
    public void operation_after_show_ui(){

        adId = (SimpleDraweeView) findViewById(R.id.adId);

        if(null==countDown)countDown = new CountDown(6000 , 1000 , countDownId , splashId , this , "local_default");

        countDown.start();

    }

    /**
     * @param view
     * Description :打开 加载上来的广告
     */
    public void openAdvertise(View view){

    }
    @Override
    public boolean onKeyDown(int keycode , KeyEvent event){
        if(KeyEvent.KEYCODE_BACK == keycode || KeyEvent.KEYCODE_MENU == keycode){
            return true;
        }
        return super.onKeyDown(keycode , event);
    }

    @Override
    public <T> void fragmentCallbackActivity(T who){

        if (ad != null) {
            Uri uri = Uri.parse(ad.getImg_uri());
            adId.setImageURI(uri);
            adId.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT , RelativeLayout.LayoutParams.MATCH_PARENT);
            adId.setLayoutParams(lp);
            countDownId.setVisibility(View.VISIBLE);
            countDown = new CountDown(6000 , 1000 , countDownId , adId , this , "load_network_advertise");
            countDown.start();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}
