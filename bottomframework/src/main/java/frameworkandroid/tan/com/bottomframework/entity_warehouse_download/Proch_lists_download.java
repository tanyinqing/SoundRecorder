package frameworkandroid.tan.com.bottomframework.entity_warehouse_download;

import java.io.Serializable;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.entity_warehouse.Proch;


/**
 * Created by Administrator on 2018/3/1 0001.
 */
public class Proch_lists_download implements Serializable{
    private List<Proch> list;
    private String total;

    @Override
    public String toString() {
        return "Proch_lists_download{" +
                "list=" + list +
                ", total='" + total + '\'' +
                '}';
    }

    public List<Proch> getList() {
        return list;
    }

    public void setList(List<Proch> list) {
        this.list = list;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
