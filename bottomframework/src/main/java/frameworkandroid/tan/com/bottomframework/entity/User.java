package frameworkandroid.tan.com.bottomframework.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/11 0011.
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String U_organize;
    private String limit;
    private String msg;
    private String success;

    @Override
    public String toString() {
        return "User_andbass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", U_organize='" + U_organize + '\'' +
                ", limit='" + limit + '\'' +
                ", msg='" + msg + '\'' +
                ", success='" + success + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getU_organize() {
        return U_organize;
    }

    public void setU_organize(String u_organize) {
        U_organize = u_organize;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
