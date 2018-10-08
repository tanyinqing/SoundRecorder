package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class Location_info implements Serializable {
    private String location_id;
    private String location_name;
    private Num num;

    @Override
    public String toString() {
        return "Location_info{" +
                "location_id='" + location_id + '\'' +
                ", location_name='" + location_name + '\'' +
                ", num=" + num +
                '}';
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Num getNum() {
        return num;
    }

    public void setNum(Num num) {
        this.num = num;
    }
}
