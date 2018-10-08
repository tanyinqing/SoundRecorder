package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class Park_goods implements Serializable {
    private String sku;
    private String goods_name;
    private String goods_num;
    private List<Location_info> location_info;

    @Override
    public String toString() {
        return "Park_goods{" +
                "sku='" + sku + '\'' +
                ", goods_name='" + goods_name + '\'' +
                ", goods_num='" + goods_num + '\'' +
                ", location_info=" + location_info +
                '}';
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public List<Location_info> getLocation_info() {
        return location_info;
    }

    public void setLocation_info(List<Location_info> location_info) {
        this.location_info = location_info;
    }
}
