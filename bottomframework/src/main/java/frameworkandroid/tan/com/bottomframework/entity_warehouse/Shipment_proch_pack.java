package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class Shipment_proch_pack implements Serializable {
    private String boxsn;
    private List<Park_goods> goods;

    @Override
    public String toString() {
        return "Shipment_proch_pack{" +
                "boxsn='" + boxsn + '\'' +
                ", goods=" + goods +
                '}';
    }

    public String getBoxsn() {
        return boxsn;
    }

    public void setBoxsn(String boxsn) {
        this.boxsn = boxsn;
    }

    public List<Park_goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Park_goods> goods) {
        this.goods = goods;
    }
}
