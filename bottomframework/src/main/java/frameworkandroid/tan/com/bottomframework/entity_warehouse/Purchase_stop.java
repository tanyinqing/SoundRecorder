package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class Purchase_stop implements Serializable {
    private String goodName;
    private String addNum;
    private String addLocation;

    @Override
    public String toString() {
        return "Purchase_stop{" +
                "goodName='" + goodName + '\'' +
                ", addNum='" + addNum + '\'' +
                ", addLocation='" + addLocation + '\'' +
                '}';
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getAddNum() {
        return addNum;
    }

    public void setAddNum(String addNum) {
        this.addNum = addNum;
    }

    public String getAddLocation() {
        return addLocation;
    }

    public void setAddLocation(String addLocation) {
        this.addLocation = addLocation;
    }
}
