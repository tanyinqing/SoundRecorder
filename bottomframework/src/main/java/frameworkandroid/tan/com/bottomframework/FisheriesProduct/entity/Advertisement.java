package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import java.io.Serializable;

/**
 * ���
 * @author ־ǿ
 *
 */
public class Advertisement implements Serializable{
	/*
	private String content; //�������
	private String createTime;
	private String id;//���id
	private String name;//�������
	private String isChain; //�Ƿ������ӹ��
	private String picurl;//ͼƬ��ַ
	 */
	private String content; //�������
	private String createTime;
	private String id;//���id
	private String title;//�������
	private String adShow; //�Ƿ������ӹ��
	private String image;//ͼƬ��ַ

	@Override
	public String toString() {
		return "Advertisement{" +
				"content='" + content + '\'' +
				", createTime='" + createTime + '\'' +
				", id='" + id + '\'' +
				", title='" + title + '\'' +
				", adShow='" + adShow + '\'' +
				", image='" + image + '\'' +
				'}';
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return title;
	}
	public void setName(String name) {
		this.title = name;
	}
	public String getIsChain() {
		return adShow;
	}
	public void setIsChain(String isChain) {
		this.adShow = isChain;
	}
	public String getPicurl() {
		return image;
	}
	public void setPicurl(String picurl) {
		this.image = picurl;
	}
	
	
	
}
