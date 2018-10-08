package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
public class Location_goods implements Serializable {

    private Boolean isSelected;
    private String location_name;
    private int location_id;
    private String amount;
    private String unit;
    private int parent_id;

    @Override
    public String toString() {
        return "Location_goods{" +
                "isSelected=" + isSelected +
                ", location_name='" + location_name + '\'' +
                ", location_id=" + location_id +
                ", amount='" + amount + '\'' +
                ", unit='" + unit + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
