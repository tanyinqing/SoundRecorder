package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class CategoryTree implements Serializable {

    private int id;
    private int has_child;
    private String name;
    private Boolean isFold;  //是否展开
    private Boolean isSelected;  //这个大类是否选中
    private List<CategoryTreeChildren> children;

    @Override
    public String toString() {
        return "CategoryTree{" +
                "id=" + id +
                ", has_child=" + has_child +
                ", name='" + name + '\'' +
                ", isFold=" + isFold +
                ", children=" + children +
                '}';
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHas_child() {
        return has_child;
    }

    public void setHas_child(int has_child) {
        this.has_child = has_child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsFold() {
        return isFold;
    }

    public void setIsFold(Boolean isFold) {
        this.isFold = isFold;
    }

    public List<CategoryTreeChildren> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryTreeChildren> children) {
        this.children = children;
    }
}

