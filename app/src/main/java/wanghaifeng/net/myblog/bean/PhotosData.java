package wanghaifeng.net.myblog.bean;

import java.util.ArrayList;


/**
 * 组图的数据封装
 */
public class PhotosData {

    public ArrayList<NewsEntity> result;


    public  class NewsEntity {
        public String tg_id;
        public String tg_face;
        public String tg_date;
        public String tg_name;

    }

}
