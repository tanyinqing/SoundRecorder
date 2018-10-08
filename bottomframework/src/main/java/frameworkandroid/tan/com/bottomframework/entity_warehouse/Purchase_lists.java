package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class Purchase_lists implements Serializable {
    private String id;
    private String purchase_sn;
    private String created_at;

    @Override
    public String toString() {
        return "Purchase_lists{" +
                "id='" + id + '\'' +
                ", purchase_sn='" + purchase_sn + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurchase_sn() {
        return purchase_sn;
    }

    public void setPurchase_sn(String purchase_sn) {
        this.purchase_sn = purchase_sn;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
