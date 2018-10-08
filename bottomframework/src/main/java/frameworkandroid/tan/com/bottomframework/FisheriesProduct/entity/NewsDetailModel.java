package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 
 * @date 2016-9-12 详细新闻实体类
 * 
 */
public class NewsDetailModel implements Serializable {

	private String postId;

	private String title;

	private String body;

	private String ptime;

	private String shareLink;
	private List<Images> img;

	private ArrayList<String> img1;

	public NewsDetailModel() {
		// TODO Auto-generated constructor stub

		img1 = new ArrayList<String>();
		img = new ArrayList<Images>();
	}

	@Override
	public String toString() {
		return "NewsDetailModel{" +
				"postId='" + postId + '\'' +
				", title='" + title + '\'' +
				", body='" + body + '\'' +
				", ptime='" + ptime + '\'' +
				", shareLink='" + shareLink + '\'' +
				", img=" + img +
				", img1=" + img1 +
				'}';
	}

	public List<Images> getImg() {
		return img;
	}

	public void setImg(List<Images> img) {
		this.img = img;
	}

	public String getShareLink() {
		return shareLink;
	}

	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}

	public String getPtime() {
		return ptime;
	}

	public void setPtime(String ptime) {
		this.ptime = ptime;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public ArrayList<String> getImg1() {
		for (Images image:img ){
			img1.add(image.getSrc());
		}
		return img1;
	}

	public void setImg1(ArrayList<String> img1) {
		this.img1 = img1;
	}

}
