package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class CategoryTreeChildren implements Serializable {
    private int has_child;
    private int id;
    private int superId;//父类的id
    private String name;
    private Boolean isSelected;//定义这个数据库是否被选中

    @Override
    public String toString() {
        return "CategoryTreeChildren{" +
                "has_child=" + has_child +
                ", id=" + id +
                ", superId=" + superId +
                ", name='" + name + '\'' +
                ", isSelected=" + isSelected +
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

    public int getSuperId() {
        return superId;
    }

    public void setSuperId(int superId) {
        this.superId = superId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }
}

