package com.qbwc.mytest.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/23 0023 上午 11:07
 */
public class GuideAdapter extends PagerAdapter{

    private ImageView[] ivs;

    public GuideAdapter(ImageView[] ivs){
        this.ivs = ivs;
    }
    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return ivs.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(ivs[position], 0);
        return ivs[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(ivs[position]);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
