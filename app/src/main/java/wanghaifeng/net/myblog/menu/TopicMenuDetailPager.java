package wanghaifeng.net.myblog.menu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import wanghaifeng.net.myblog.R;
import wanghaifeng.net.myblog.activity.MainActivity;
import wanghaifeng.net.myblog.bean.MenuTabData;
import wanghaifeng.net.myblog.impl.ArticlesListPager;

/**
 * 菜单详情页-专题
 */
public class TopicMenuDetailPager extends BaseMenuTabsPager implements ViewPager.OnPageChangeListener {

    private ViewPager vpNewsMenu;
    private TabPageIndicator indicator;//Tab标签页
    private List<ArticlesListPager> mTabPagerList;//标签详情页
    private List<MenuTabData.articlesTabData> mArticlesTabData;

    public TopicMenuDetailPager(Activity activity, List<MenuTabData.articlesTabData> children) {
        super(activity);
        mArticlesTabData = children;
    }

    @Override
    public View initView() {

        View view = View.inflate(mActivity, R.layout.news_menu_detail, null);//用来实例化xml文件为view对象
        vpNewsMenu = (ViewPager) view.findViewById(R.id.vp_news_menu);
        ImageButton nextPager= (ImageButton) view.findViewById(R.id.imbtn_next_pager);
        nextPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //点击或滑动的时候的动作处理
                int currentItem = vpNewsMenu.getCurrentItem();
                vpNewsMenu.setCurrentItem(++currentItem);
            }
        });

        indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        indicator.setOnPageChangeListener(this);//当viewpager与indicator绑定时，滑动监听设置给indicator

        return view;
    }

    //加载tab标签(iOS开发,安卓开发,前端开发.......)
    @Override
    public void initData() {
        mTabPagerList = new ArrayList<>();
        for (int i = 0; i < mArticlesTabData.size(); i++) {
            System.out.println("======标签遍历解析结果：" + mArticlesTabData.get(i));
            ArticlesListPager pager = new ArticlesListPager(mActivity, mArticlesTabData.get(i));
            mTabPagerList.add(pager);
        }

        vpNewsMenu.setAdapter(new MenuDetailAdapter());
        indicator.setViewPager(vpNewsMenu);//必须设置完viewpager的adapter

    }

    //滑动监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        MainActivity mainUi= (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUi.getSlidingMenu();
        if(position==0){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class MenuDetailAdapter extends PagerAdapter {

        //重写此方法用于，返回页面标题，用于页签显示
        @Override
        public CharSequence getPageTitle(int position) {
            return mArticlesTabData.get(position).tg_title;
        }

        @Override
        public int getCount() {
            return mTabPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ArticlesListPager pager = mTabPagerList.get(position);
            container.addView(pager.mRootView);
            pager.initData();
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
