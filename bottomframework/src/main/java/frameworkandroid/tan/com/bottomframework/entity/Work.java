package frameworkandroid.tan.com.bottomframework.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class Work implements Serializable {
    private int id;
    private String data;
    private String function;
    private String time;
    private String image_url;

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", function='" + function + '\'' +
                ", time='" + time + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}

