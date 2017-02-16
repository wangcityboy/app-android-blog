package wanghaifeng.net.myblog.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinaskin on 16/10/9.
 */

public class AdvertisePhotoData {

    public ArrayList<TopPhotoData> result;

    public class TopPhotoData {
        public String tg_id;
        public String tg_image;
        public String tg_content;
        public String tg_url;
        public String tg_type;

        @Override
        public String toString() {
            return "advertiseData{" +
                    "result=" + result +
                    '}';
        }

    }

}
