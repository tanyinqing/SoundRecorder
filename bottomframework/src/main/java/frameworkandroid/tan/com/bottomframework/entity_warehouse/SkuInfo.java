package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class SkuInfo implements Serializable {
    private String sku_id;
    private String goods_name;
    private String sku_num;
    private String seller;
    private String time;
    private String num;

    @Override
    public String toString() {
        return "SkuInfo{" +
                "sku_id='" + sku_id + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", sku_num='" + sku_num + '\'' +
                ", seller='" + seller + '\'' +
                ", time='" + time + '\'' +
                ", num='" + num + '\'' +
                '}';
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

    public String getSku_num() {
        return sku_num;
    }

    public void setSku_num(String sku_num) {
        this.sku_num = sku_num;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
