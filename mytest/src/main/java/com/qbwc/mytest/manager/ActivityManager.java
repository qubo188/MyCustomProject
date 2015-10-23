package com.qbwc.mytest.manager;

import android.app.Activity;
import android.content.Intent;

import com.qbwc.mytest.utils.LogUtils;

import java.util.Stack;

/**
 * 项目名称：MyCustomProject
 * 类描述：
 * 创建人：qubo
 * 创建时间：2015/10/21 0021 下午 5:11
 */
public class ActivityManager {

    private static ActivityManager am;

    // Description : Activity 的 stack列表。主要用于管理 Activity
    Stack<Activity> activityStack = new Stack<Activity>();


    public static ActivityManager instance() {
        if (null == am) {
            am = new ActivityManager();
        }
        return am;
    }

    /**
     * @return Activity
     * Description : 从栈中取出 最后压入的 Activity .进行返回
     */
    public Activity getCurrentActivity() {
        Activity activity = null;
        if (!activityStack.empty()) {
            activity = activityStack.lastElement();
        }
        return activity;
    }

    /**
     * @param activity 要压入的 activity
     *                 Description : 将 Activity存入 栈中
     */
    public void pushActivity(Activity activity) {
        boolean flag = false;
        for (Activity act : activityStack) {
            if (act.getClass() == activity.getClass()) {
                flag = true;
                break;
            }
        }
        if (!flag) activityStack.add(activity);
        LogUtils.pdebug("Push");
    }

    /**
     * @param activity 要移除的 activity
     *                 Description : 从 Activity的管理栈中 将 activity删除
     */
    public void popActivity(Activity activity) {
        activityStack.remove(activity);
        activity.finish();
        LogUtils.pdebug("Pop one");
    }

    /**
     * @param cls Description : 删除 除 传递进来的cls以外的所有 activity
     */
    public void popOtherActivity(Class cls) {
        for (Activity activity : activityStack) {
            if (null != activity && activity.getClass().equals(cls)) {
                continue;
            }
            activity.finish();
            popActivity(activity);
        }
    }

    /**
     * Description : 删除 activityStack中所有的 Activity
     */
    public void popAllActivity() {
        for (Activity activity : activityStack) {
            if (null != activity) {
                activity.finish();
                popActivity(activity);
            }
        }
    }

    /**
     * @param cls 要启动的 activity
     * @throws Exception 如果ActivityStack中是空。就抛出 null
     *                   Description : 一 将 activity加入到 stack中
     *                   二 启动一个新的 activity
     */
    public void startNextActivity(Class cls) {

        Activity currentActivity = getCurrentActivity();
        if (null == currentActivity) try {
            throw new ClassNotFoundException("当前ActivityStack中没有activity存在。无法启动下一个Activity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(currentActivity, cls);
        currentActivity.startActivity(intent);


    }
}
