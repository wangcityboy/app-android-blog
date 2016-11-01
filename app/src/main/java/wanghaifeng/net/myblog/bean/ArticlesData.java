package wanghaifeng.net.myblog.bean;

import java.util.ArrayList;

/**
 * 页签详情页数据
 *
 * @author wanghaifeng
 *
 */
public class ArticlesData {


	public ArrayList<TabArticleData> result;


	/**
	 * 日志列表对象
	 *
	 * @author hdison
	 *
	 */
	public class TabArticleData {
		public String tg_id;
		public String tg_image;
		public String tg_username;
		public String tg_title;
		public String tg_classify;
		public String tg_content;
		public String tg_readcount;
		public String tg_date;

		@Override
		public String toString() {
			return "TabArticleData [tg_title=" + tg_title + "]";
		}
	}

	/**
	 * 头条轮播图对象
	 *
	 * @author hdison
	 *
	 */
	public class TopPhotoData {
		public String tg_id;
		public String tg_image;
		public String tg_content;
		public String tg_type;
		public String tg_url;

		@Override
		public String toString() {
			return "TopPhotoData [tg_image=" + tg_image + ", tg_type=" + tg_type
					+ ", tg_content=" + tg_content+"]";
		}

	}

	@Override
	public String toString() {
		return "TabData [result=" + result + "]";
	}

}
