package frameworkandroid.tan.com.bottomframework.entity_warehouse_download;

import java.io.Serializable;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.entity_warehouse.Repertory_date;


/**
 * Created by Administrator on 2018/3/12 0012.
 */
public class Repertory_lists_download implements Serializable {
    private List<Repertory_date> data;
    private int Total;
    private String location_name;

    @Override
    public String toString() {
        return "Repertory_lists_download{" +
                "data=" + data +
                ", total=" + Total +
                '}';
    }

    public List<Repertory_date> getList() {
        return data;
    }

    public void setList(List<Repertory_date> list) {
        this.data = list;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        this.Total = total;
    }
}
