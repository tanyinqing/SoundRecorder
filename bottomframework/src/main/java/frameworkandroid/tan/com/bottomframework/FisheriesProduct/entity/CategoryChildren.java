package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class CategoryChildren implements Serializable {

    private int has_child;
    private int id;
    private int parent;
    private String name;

    @Override
    public String toString() {
        return "CategoryChildren{" +
                "has_child=" + has_child +
                ", id=" + id +
                ", parent=" + parent +
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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

