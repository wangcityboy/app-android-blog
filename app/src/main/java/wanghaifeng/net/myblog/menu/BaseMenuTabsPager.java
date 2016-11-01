package wanghaifeng.net.myblog.menu;

import android.app.Activity;
import android.view.View;

/**
 * 菜单详情页的基类
 */
public abstract class BaseMenuTabsPager {

    public Activity mActivity;
    public View mRootView;//界面布局文件

    public BaseMenuTabsPager(Activity activity){
        mActivity=activity;
        mRootView=initView();
    }

    //初始化界面
    public abstract View initView();

    //初始化数据
    public void initData(){}

}
