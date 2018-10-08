package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class Num implements Serializable {
    private String minNum;
    private String unitName;
    private String min_unit_id;

    @Override
    public String toString() {
        return "Num{" +
                "minNum='" + minNum + '\'' +
                ", unitName='" + unitName + '\'' +
                ", min_unit_id='" + min_unit_id + '\'' +
                '}';
    }

    public String getMinNum() {
        return minNum;
    }

    public void setMinNum(String minNum) {
        this.minNum = minNum;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getMin_unit_id() {
        return min_unit_id;
    }

    public void setMin_unit_id(String min_unit_id) {
        this.min_unit_id = min_unit_id;
    }
}
