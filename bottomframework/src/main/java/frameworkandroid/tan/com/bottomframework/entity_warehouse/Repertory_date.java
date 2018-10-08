package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
public class Repertory_date implements Serializable {
    private List<Good> goods;
    private String location_name;
    private String location_id;

    private String location;
    private String goods_name;
    private String sku_id;
    private String amount;
    private String total;
    private Boolean isExpand;

    private List<Rep> rep;

    @Override
    public String toString() {
        return "Repertory_date{" +
                "goods=" + goods +
                ", location_name='" + location_name + '\'' +
                ", location_id='" + location_id + '\'' +
                '}';
    }

    public Boolean getIsExpand() {
        return isExpand;
    }

    public void setIsExpand(Boolean isExpand) {
        this.isExpand = isExpand;
    }

    public List<Rep> getRep() {
        return rep;
    }

    public void setRep(List<Rep> rep) {
        this.rep = rep;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }
}
