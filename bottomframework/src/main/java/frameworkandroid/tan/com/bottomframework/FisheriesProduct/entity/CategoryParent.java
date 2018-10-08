package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class CategoryParent implements Serializable {
    private int has_child;
    private int id;
    private String name;

    @Override
    public String toString() {
        return "CategoryParent{" +
                "has_child=" + has_child +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getHas_child() {
        return has_child;
    }

    public void setHas_child(int has_child) {
        this.has_child = has_child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

