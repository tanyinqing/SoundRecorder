package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class NewsEventList implements Serializable {
    private int id;
    private String title;
    private String omit_content;
    private String create_time;

    @Override
    public String toString() {
        return "NewsEventList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", omit_content='" + omit_content + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOmit_content() {
        return omit_content;
    }

    public void setOmit_content(String omit_content) {
        this.omit_content = omit_content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

