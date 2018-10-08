package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class Porch_info  implements Serializable {
    private String prochsn;
    private String proch_time;
    private String proch_person_name;
    private String proch_person_phone;
    private List<Good> goods;
    private String remark;

    @Override
    public String toString() {
        return "Porch_info{" +
                "prochsn='" + prochsn + '\'' +
                ", proch_time='" + proch_time + '\'' +
                ", proch_person_name='" + proch_person_name + '\'' +
                ", proch_person_phone='" + proch_person_phone + '\'' +
                ", goods=" + goods +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getProchsn() {
        return prochsn;
    }

    public void setProchsn(String prochsn) {
        this.prochsn = prochsn;
    }

    public String getProch_time() {
        return proch_time;
    }

    public void setProch_time(String proch_time) {
        this.proch_time = proch_time;
    }

    public String getProch_person_name() {
        return proch_person_name;
    }

    public void setProch_person_name(String proch_person_name) {
        this.proch_person_name = proch_person_name;
    }

    public String getProch_person_phone() {
        return proch_person_phone;
    }

    public void setProch_person_phone(String proch_person_phone) {
        this.proch_person_phone = proch_person_phone;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
