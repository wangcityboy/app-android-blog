package wanghaifeng.net.myblog.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import wanghaifeng.net.myblog.R;
import wanghaifeng.net.myblog.impl.BasePager;
import wanghaifeng.net.myblog.menu.BaseMenuListPager;


/**
 * Created by acer-pc on 2015/11/11.
 */
public class ContentFragment extends BaseFragment {

    private ViewPager vpContent;
    private List<BasePager> mPageList;


    @Override
    public View initViews() {

        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        vpContent= (ViewPager) view.findViewById(R.id.vp_content);

        return view;
    }

    @Override
    public void initData() {

        mPageList=new ArrayList<>();
        mPageList.add(new BaseMenuListPager(mActivity));

        vpContent.setAdapter(new ContentAdapter());

        //通过viewPager的选择事件实现预加载数据
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPageList.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //手动加载第一页的数据
        mPageList.get(0).initData();
    }

    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager=mPageList.get(position);
//            pager.initData(); 不能在这里初始化数据，因为会预加载
            container.addView(pager.mRootView);
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 获取加载第一页的数据
     */
    public BaseMenuListPager getNewsPager(){
        BaseMenuListPager newsPager= (BaseMenuListPager) mPageList.get(0);
        return newsPager;
    }
}
