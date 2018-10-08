package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
public class Repertory_goods_info_download  implements Serializable {
    private List<Location_goods> location;
    private String goods_name;
    private String sku_id;

    @Override
    public String toString() {
        return "Repertory_goods_info_download{" +
                "location=" + location +
                ", goods_name='" + goods_name + '\'' +
                '}';
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public List<Location_goods> getLocation() {
        return location;
    }

    public void setLocation(List<Location_goods> location) {
        this.location = location;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
}
