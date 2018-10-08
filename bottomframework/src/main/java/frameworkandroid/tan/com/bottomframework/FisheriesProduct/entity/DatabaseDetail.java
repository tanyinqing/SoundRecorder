package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class DatabaseDetail implements Serializable {
    private String name;
    private String value;

    @Override
    public String toString() {
        return "DatabaseDetail{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

