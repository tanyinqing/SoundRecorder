package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/12 0012.
 */
public class Rep implements Serializable {
    private String amount;
    private String location;

    @Override
    public String toString() {
        return "Rep{" +
                "amount='" + amount + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
