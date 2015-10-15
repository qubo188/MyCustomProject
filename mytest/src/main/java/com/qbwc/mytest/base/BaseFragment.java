package com.qbwc.mytest.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 项目名称：MyCustomProject
 * 类描述：Fragment的基类
 * 创建人：qubo
 * 创建时间：2015/10/13 0013 上午 9:59
 * 修改人：qubo
 */
public class BaseFragment extends Fragment {

    private int layoutId;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {

            operation_before_show_fragment();
            view = inflater.from(getActivity()).inflate(setLayoutId(), null);
            operation_after_show_fragment();

            // Butterknife注入 Fragment
            ButterKnife.inject(this,view);

        }

        return view;
    }

    /**
     * Description fragment 显示之后的操作
     */
    protected void operation_after_show_fragment() {

    }

    /**
     * Description fragment显示之前的操作
     */
    protected void operation_before_show_fragment() {

    }

    /**
     * Description 设置 fragment显示的布局
     */
    public int setLayoutId() {
        return layoutId;
    }
}
