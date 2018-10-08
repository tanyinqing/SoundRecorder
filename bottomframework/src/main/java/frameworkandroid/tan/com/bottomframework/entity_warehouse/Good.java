package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class Good implements Serializable {
    private Boolean ck_selecked;
    private String goods_name;
    private String name;
    private String num;
    private String sku_id;
    private String unit;
    private String location;
    private String unit_id;
    private String amount;
    private Boolean stock;
    private String total;
    private List<Rep> rep;

    @Override
    public String toString() {
        return "Good{" +
                "ck_selecked=" + ck_selecked +
                ", goods_name='" + goods_name + '\'' +
                ", num='" + num + '\'' +
                ", sku_id='" + sku_id + '\'' +
                ", unit='" + unit + '\'' +
                ", location='" + location + '\'' +
                ", amount='" + amount + '\'' +
                ", stock=" + stock +
                ", total='" + total + '\'' +
                ", rep=" + rep +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Boolean getCk_selecked() {
        return ck_selecked;
    }

    public void setCk_selecked(Boolean ck_selecked) {
        this.ck_selecked = ck_selecked;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }
}
