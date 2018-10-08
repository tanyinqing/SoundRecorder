package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class Shipment_pack implements Serializable {
    private String ship_status;
    private String prochsn;
    private String short_code;
    private List<ReduceSku_info> reduceSku_info;

    @Override
    public String toString() {
        return "Shipment_pack{" +
                "ship_status='" + ship_status + '\'' +
                ", prochsn='" + prochsn + '\'' +
                ", short_code='" + short_code + '\'' +
                ", reduceSku_info=" + reduceSku_info +
                '}';
    }

    public String getShip_status() {
        return ship_status;
    }

    public void setShip_status(String ship_status) {
        this.ship_status = ship_status;
    }

    public String getProchsn() {
        return prochsn;
    }

    public void setProchsn(String prochsn) {
        this.prochsn = prochsn;
    }

    public String getShort_code() {
        return short_code;
    }

    public void setShort_code(String short_code) {
        this.short_code = short_code;
    }

    public List<ReduceSku_info> getReduceSku_info() {
        return reduceSku_info;
    }

    public void setReduceSku_info(List<ReduceSku_info> reduceSku_info) {
        this.reduceSku_info = reduceSku_info;
    }
}
