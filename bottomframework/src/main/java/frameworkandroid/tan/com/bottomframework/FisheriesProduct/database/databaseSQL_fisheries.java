package frameworkandroid.tan.com.bottomframework.FisheriesProduct.database;

/**
 * Created by Administrator on 2018/8/31 0031.
 */
public class databaseSQL_fisheries {
    /*
   *    日志表建表语句
   *    CREATE TABLE RiZhi (id INTEGER primary key autoincrement,[U_Id] text,[Date] text,[IT_Number] text,[Content] text);
   */
    public static final String CREATE_RiZhi = "create table RiZhi ("
            + "id integer NOT NULL primary key autoincrement,"//注释 id 自增长
            + "[U_Id] text, "
            + "[Date] text, "
            + "[IT_Number] text, "
            + "[Content] text)";//次数
}
