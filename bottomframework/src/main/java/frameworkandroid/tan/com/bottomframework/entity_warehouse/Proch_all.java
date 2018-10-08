package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class Proch_all implements Serializable {
    private Boolean selecked;
    private String sku_id;
    private String goods_name;
    private String num;
    private String unit;
    private String unit_id;
    private String name;

    @Override
    public String toString() {
        return "Proch_all{" +
                "sku_id='" + sku_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", num='" + num + '\'' +
                ", unit='" + unit + '\'' +
                ", unit_id='" + unit_id + '\'' +
                '}';
    }

    public Boolean getSelecked() {
        return selecked;
    }

    public void setSelecked(Boolean selecked) {
        this.selecked = selecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }
}
