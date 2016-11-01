package wanghaifeng.net.myblog.menu;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.util.List;
import wanghaifeng.net.myblog.R;
import wanghaifeng.net.myblog.bean.PhotosData;
import wanghaifeng.net.myblog.global.GlobalContants;
import wanghaifeng.net.myblog.utils.CacheUtils;
import wanghaifeng.net.myblog.utils.bitmap.MyBitmapUtils;

/**
 * 菜单详情页-组图
 */
public class PhotoMenuDetailPager extends BaseMenuTabsPager {


    private ListView lvPhoto;
    private GridView gvPhoto;
    private List<PhotosData.NewsEntity> mNewsList;
    private PhotoAdapter mPhotoAdapter;

    private ImageButton btnPhotoType;//组图切换按钮

    public PhotoMenuDetailPager(Activity activity, ImageButton imBtnPhotoType) {
        super(activity);

        this.btnPhotoType=imBtnPhotoType;

        btnPhotoType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePhotoType();
            }
        });
    }

    /**
     * 切换组图显示状态
     */
    private boolean isListPhotoType=true;

    private void changePhotoType() {
        if(isListPhotoType){
            btnPhotoType.setImageResource(R.mipmap.icon_pic_grid_type);
            lvPhoto.setVisibility(View.GONE);
            gvPhoto.setVisibility(View.VISIBLE);
            isListPhotoType=false;
        }else {
            btnPhotoType.setImageResource(R.mipmap.icon_pic_list_type);
            gvPhoto.setVisibility(View.GONE);
            lvPhoto.setVisibility(View.VISIBLE);
            isListPhotoType=true;
        }
    }

    @Override
    public View initView() {

        View view=View.inflate(mActivity, R.layout.menu_photo_pager, null);

        lvPhoto = (ListView) view.findViewById(R.id.lv_photo);
        gvPhoto = (GridView) view.findViewById(R.id.gv_photo);

        return view;
    }

    @Override
    public void initData() {

        String cache = CacheUtils.getCache(mActivity, GlobalContants.PHOTODIR_URL);
        if(!TextUtils.isEmpty(cache)){
            parseData(cache);
        }
        getDataFromServer();
    }

    /**
     * 从服务器获取数据
     */
    private void getDataFromServer() {
        RequestParams params=new RequestParams(GlobalContants.PHOTODIR_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("====////====相册列表"+result);
                parseData(result);

                //设置缓存
                CacheUtils.setCache(mActivity,GlobalContants.PHOTODIR_URL,result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFinished() {

            }
        });

    }

    /**
     * 解析从服务器获取的数据
     * @param result
     */
    private void parseData(String result) {
        Gson gson=new Gson();
        PhotosData data = gson.fromJson(result, PhotosData.class);

        mNewsList = data.result;

        if(mPhotoAdapter==null){
            mPhotoAdapter=new PhotoAdapter();
            lvPhoto.setAdapter(mPhotoAdapter);
            gvPhoto.setAdapter(mPhotoAdapter);
        }

    }

    /**
     * 图片数据适配器
     */
    class PhotoAdapter extends BaseAdapter{

        private MyBitmapUtils utils;//自定义的BitmapUtils

        public PhotoAdapter(){
            utils=new MyBitmapUtils();//
        }

        @Override
        public int getCount() {
            return mNewsList.size();
        }

        @Override
        public PhotosData.NewsEntity getItem(int position) {
            return mNewsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if(convertView==null){
                convertView=View.inflate(mActivity,R.layout.list_photo_item,null);
                holder=new ViewHolder(convertView);

                convertView.setTag(holder);
            }else {
                holder= (ViewHolder) convertView.getTag();
            }

            PhotosData.NewsEntity item = getItem(position);

            holder.tvTltle.setText(item.tg_name);

            System.out.println("====]]]]]"+GlobalContants.HOMESEVER_URL+item.tg_face);
            utils.disPlay(holder.ivPic,GlobalContants.HOMESEVER_URL+item.tg_face);

            return convertView;
        }

        class ViewHolder{

            private ImageView ivPic;
            private TextView tvTltle;

            public ViewHolder(View view){
                ivPic= (ImageView) view.findViewById(R.id.iv_pic);
                tvTltle= (TextView) view.findViewById(R.id.tv_title);
            }

        }
    }
}
