package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class Proch implements Serializable {
    private String prochsn;
    private String store;
    private String proch_status;
    private Boolean stock;
    private String seller;
    private String status_name;
    private String merge_orderSn;

    @Override
    public String toString() {
        return "Proch{" +
                "prochsn='" + prochsn + '\'' +
                ", store='" + store + '\'' +
                ", proch_status='" + proch_status + '\'' +
                ", stock='" + stock + '\'' +
                ", seller='" + seller + '\'' +
                ", status_name='" + status_name + '\'' +
                ", merge_orderSn='" + merge_orderSn + '\'' +
                '}';
    }

    public String getProchsn() {
        return prochsn;
    }

    public void setProchsn(String prochsn) {
        this.prochsn = prochsn;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getProch_status() {
        return proch_status;
    }

    public void setProch_status(String proch_status) {
        this.proch_status = proch_status;
    }

    public Boolean getStock() {
        return stock;
    }

    public void setStock(Boolean stock) {
        this.stock = stock;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getMerge_orderSn() {
        return merge_orderSn;
    }

    public void setMerge_orderSn(String merge_orderSn) {
        this.merge_orderSn = merge_orderSn;
    }
}
