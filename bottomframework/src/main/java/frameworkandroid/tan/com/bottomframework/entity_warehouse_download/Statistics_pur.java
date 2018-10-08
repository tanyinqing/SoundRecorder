package frameworkandroid.tan.com.bottomframework.entity_warehouse_download;

import java.io.Serializable;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.entity_warehouse.SkuInfo;


/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class Statistics_pur  implements Serializable {
    private String purNum;
    private String purIds;
    private String purCost;
    private List<SkuInfo> skuInfo;

    @Override
    public String toString() {
        return "Statistics_pur{" +
                "purNum='" + purNum + '\'' +
                ", purIds='" + purIds + '\'' +
                ", purCost='" + purCost + '\'' +
                ", skuInfo=" + skuInfo +
                '}';
    }

    public String getPurNum() {
        return purNum;
    }

    public void setPurNum(String purNum) {
        this.purNum = purNum;
    }

    public String getPurIds() {
        return purIds;
    }

    public void setPurIds(String purIds) {
        this.purIds = purIds;
    }

    public String getPurCost() {
        return purCost;
    }

    public void setPurCost(String purCost) {
        this.purCost = purCost;
    }

    public List<SkuInfo> getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(List<SkuInfo> skuInfo) {
        this.skuInfo = skuInfo;
    }
}
