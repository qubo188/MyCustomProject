package com.qbwc.mytest.ui;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qbwc.mytest.R;
import com.qbwc.mytest.adapter.GuideAdapter;
import com.qbwc.mytest.animation.DepthPageTransformer;
import com.qbwc.mytest.base.BaseActivity;
import com.qbwc.mytest.manager.ActivityManager;

import butterknife.Bind;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/15 0015 上午 11:45
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    @Bind(R.id.guidViewPageId)
    ViewPager guidViewPageId;

    @Bind(R.id.pageTip_LL_Id)
    LinearLayout pageTip_LL_Id;

    //用于装载 ImageView的切换小点
    private ImageView[] guidePoints = new ImageView[3];

    // 引导 页面 图片
    private int[] viewPagerData = {R.drawable.welcome1 , R.drawable.welcome2 , R.drawable.welcome3};
    // 引导 页面 数据
    private ImageView[] guidePages;

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
        //将当前 Activity加入到管理器当中
        ActivityManager.instance().pushActivity(this);
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
            ll.setMargins(10 , 0 , 10 , 0);
            iv.setLayoutParams(ll);
            if(i==0)iv.setSelected(true);
            guidePoints[i] = iv;
            pageTip_LL_Id.addView(iv);
        }
    }

    private void initViewPager() {
        GuideAdapter ga = new GuideAdapter(guidePages);
        guidViewPageId.setAdapter(ga);
        DepthPageTransformer dptf = new DepthPageTransformer();
        guidViewPageId.setPageTransformer(true , dptf);
        guidViewPageId.setOnPageChangeListener(this);
    }


    private void ViewPagerSelectWhich(int position) {
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
}
