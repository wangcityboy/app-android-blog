package wanghaifeng.net.myblog.bean;

import java.util.List;

/**
 * News新闻数据的封装
 */
public class MenuTabData {

    public List<menuData> result;

    //侧边栏菜单数据（首页、专题、组图、互动）
    public class menuData{
        public String tg_id;
        public String tg_title;
        public int tg_type;
        public String tg_url;

        public List<articlesTabData> children;

        @Override
        public String toString() {
            return "articleMenuData{" +
                    "tg_id='" + tg_id + '\'' +
                    ", tg_title='" + tg_title + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    //日志界面下的8个tab标签数据(iOS开发,安卓开发,前端开发,后台开发,测试开发...)
    public class articlesTabData{
        public String tg_id;
        public String tg_title;
        public int tg_type;
        public String tg_url;

        @Override
        public String toString() {
            return "articlesTabData{" +
                    "tg_id='" + tg_id + '\'' +
                    ",tg_title='" + tg_title + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "articlesData{" +
                "result=" + result +
                '}';
    }
}
