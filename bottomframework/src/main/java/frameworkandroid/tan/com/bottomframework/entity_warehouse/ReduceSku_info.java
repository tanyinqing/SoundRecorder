package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class ReduceSku_info implements Serializable {
    private String sku;
    private String goods_name;
    private List<Location_info1> location_info;

    @Override
    public String toString() {
        return "ReduceSku_info{" +
                "sku='" + sku + '\'' +
                ", goods_name='" + goods_name + '\'' +
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

    public List<Location_info1> getLocation_info() {
        return location_info;
    }

    public void setLocation_info(List<Location_info1> location_info) {
        this.location_info = location_info;
    }
}
