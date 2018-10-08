package frameworkandroid.tan.com.bottomframework.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class moban_databass_entity implements Serializable {
    private String id;   //地址
    private String U_Id;   //地址
    private String Date;   //地址
    private String Content;   //地址
    private String IT_Number;   //地址

    @Override
    public String toString() {
        return "moban_databass_entity{" +
                "id='" + id + '\'' +
                ", U_Id='" + U_Id + '\'' +
                ", Date='" + Date + '\'' +
                ", Content='" + Content + '\'' +
                '}';
    }

    public String getIT_Number() {
        return IT_Number;
    }

    public void setIT_Number(String IT_Number) {
        this.IT_Number = IT_Number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getU_Id() {
        return U_Id;
    }

    public void setU_Id(String u_Id) {
        U_Id = u_Id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
