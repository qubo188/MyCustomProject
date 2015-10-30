package com.qbwc.mytest.ui;

import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.qbwc.mytest.R;
import com.qbwc.mytest.adapter.GuideAdapter;
import com.qbwc.mytest.animation.DepthPageTransformer;
import com.qbwc.mytest.base.BaseActivity;
import com.qbwc.mytest.config.AppContext;
import com.qbwc.mytest.dao.AdvertiseDao;
import com.qbwc.mytest.database.DbService;
import com.qbwc.mytest.entity.Advertise;
import com.qbwc.mytest.entity.BaseBean;
import com.qbwc.mytest.manager.ActivityManager;
import com.qbwc.mytest.network.NetWorkApi;
import com.qbwc.mytest.network.NetWorkConnectAddress;
import com.qbwc.mytest.utils.LogUtils;
import com.qbwc.mytest.utils.ParseDataUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/15 0015 上午 11:45
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener , Response.Listener , Response.ErrorListener{

    @Bind(R.id.guidViewPageId)
    ViewPager guidViewPageId;

    @Bind(R.id.pageTip_LL_Id)
    LinearLayout pageTip_LL_Id;

    @Bind(R.id.sginMainId)
    Button sginMainId;

    private ActivityManager am;

    private AdvertiseDao adDao;


    //用于装载 ImageView的切换小点
    private ImageView[] guidePoints = new ImageView[3];

    // 引导 页面 图片
    private int[] viewPagerData = {R.drawable.welcome1 , R.drawable.welcome2 , R.drawable.welcome3};
    // 引导 页面 数据
    private ImageView[] guidePages;

    /*
     * Description : 不显示 statusBar
     */
    @Override
    public boolean availableStatusBar(){
        return false;
    }
    /**
     * Description 通过传递过来的 布局文件ID。用于显示哪个界面
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_guide;
    }

    /**
     * Description 在最终显示界面之前做的操作
     */
    @Override
    public void operation_before_show_ui() {

        am = ActivityManager.instance();
    }

    /**
     * Description 操作 显示之后的 ui
     */
    @Override
    protected void operation_after_show_ui() {
        // 初始化 ViewPager的指示点
        initDataPoint();

        initDataViewPager();

        initViewPager();

        adDao = AppContext.getDaoSession(this).getAdvertiseDao();
        //请求开屏广告
        NetWorkApi.reqGetHttp("Guide", NetWorkConnectAddress.REQ_ADVERTISE_ADDRESS + "?nid=0", this, this);

    }

    @OnClick(R.id.sginMainId)
    public void sginMain(View view){
        am.startNextActivity(MainActivity.class);
        am.popActivity(this);

    }
    /**
     * 初始化 页面 的 图片
     */
    private void initDataViewPager() {
        guidePages = new ImageView[viewPagerData.length];
        for(int i = 0 ; i <= viewPagerData.length-1 ; i++){
            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(ll);
            //图片按 边界  等比例缩放
            iv.setBackgroundResource(viewPagerData[i]);
//            iv.setAdjustViewBounds(true);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);
//            iv.setImageResource(viewPagerData[i]);
            guidePages[i] = iv;
        }
    }

    /**
     * 初始化 ViewPager下面的提示小点  的 数据
     */
    private void initDataPoint() {
        for (int i = 0 ; i<=2 ; i++){
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.circle_change_state);
            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT ,LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.setMargins(10, 0, 10, 0);
            iv.setLayoutParams(ll);
            if(i==0)iv.setSelected(true);
            guidePoints[i] = iv;
            pageTip_LL_Id.addView(iv);
        }
    }

    /**
     * 初始化 ViewPager页面的 数据
     */
    private void initViewPager() {
        GuideAdapter ga = new GuideAdapter(guidePages);
        guidViewPageId.setAdapter(ga);
        DepthPageTransformer dptf = new DepthPageTransformer();
        guidViewPageId.setPageTransformer(true , dptf);
        guidViewPageId.setOnPageChangeListener(this);
    }


    /**
     * @param position 坐标。用于显示 ViewPager中哪一页
     * Description: 功能点1 ： 如果处于第三页 就显示 进入 主页面按钮。其它页面隐藏
     *              功能点2 ： 切换page页面的 小圆点的 状态
     */
    private void ViewPagerSelectWhich(int position) {
        if(position==2)sginMainId.setVisibility(View.VISIBLE);
        else sginMainId.setVisibility(View.GONE);
        for(int i = 0 ; i < guidePoints.length ; i++){
            if(i==position)guidePoints[position].setSelected(true);
            else guidePoints[i].setSelected(false);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ViewPagerSelectWhich(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK==keyCode || KeyEvent.KEYCODE_MENU==keyCode){
            return true;
        }
        return super.onKeyDown(keyCode , event);
    }

    @Override
    public void onErrorResponse(VolleyError volleyError, String s) {

    }

    @Override
    public void onResponse(Object o, String s) {
        BaseBean bb = ParseDataUtils.parse_StrToBean(BaseBean.class, o.toString());
        if(ParseDataUtils.parseSucOrErr(bb)){
            List<Advertise> data = ParseDataUtils.parseDataList(Advertise.class , bb.getData());
            if(data.size()>0){
                for(Advertise ad : data){
                    List<Advertise> advertise = adDao.queryBuilder().where(AdvertiseDao.Properties.Subtitle.eq(ad.getTitle())).list();
                    if(advertise.size()>0) LogUtils.pdebug("GuideActivity---205--->重复数据为--->"+ad.getTitle());
                    else DbService.insert(ad , adDao);
                }
            }
        }
    }

    /**
     * Destroy all fragments and loaders.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

