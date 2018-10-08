package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class GoodUpload implements Serializable {

    private String num;
    private String sku_id;
    private String unit_id;

    @Override
    public String toString() {
        return "GoodUpload{" +
                "num='" + num + '\'' +
                ", sku_id='" + sku_id + '\'' +
                ", unit_id='" + unit_id + '\'' +
                '}';
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }
}
