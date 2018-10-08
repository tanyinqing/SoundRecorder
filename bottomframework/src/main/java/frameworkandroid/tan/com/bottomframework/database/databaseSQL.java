package frameworkandroid.tan.com.bottomframework.database;

/**
 * 这个是二期的数据库的建表语句.
 */
public class databaseSQL {
    /*
    *    任务列表建表语句
    *  Addressid  地址id  通过地址id找到任务表中的所有任务
    *
    */
    public static final String CREATE_TaskListEntity = "create table TaskListEntity ("
            + "id integer NOT NULL primary key autoincrement,"//注释
            + "[AddressName] text, "   //地址名称
            + "[Addressid] text , "  //地址id
            + "[Frequency] text , "   //这个是安检的次数
            + "[Operation] text , "
            + "[SP_Id] text , "   //计划id
            + "[SP_Number] text, "  //计划编号
            + "[SerialNumber] text , "
            + "[Starttime] text, "
            + "[Stoptime] text, "

            + "[state] text, "  // 这个是一期的字段 表示安检次数  暂时没有到
            + "[LeiBie] text, "  //1 表示已上传 但并不一定全部上传完
            + "[UserId] text, "  //备用字段
            + "[ShenHeBiaoJi] text, "  //备用字段
            + "XiaQuMingCheng text)";  //备用字段
    /*
    *    任务表建表语句
    */
    public static final String CREATE_Task_entity = "create table Task_entity ("
            + "[RenWuId] integer NOT NULL primary key autoincrement,"//注释
            + "[DisNumber] text , "  //任务编号
            + "[IT_Number] text , "  //工单编号
            + "[TI_Number] text , "  //表具编号 应该设置成是唯一的 一次 二次 三次都一样
            + "[id] text , "        //地址id也相当于小区的id
            + "[operation] text , "
            + "[AnJianXiangId] text , " //该户有几个安检项
            + "[AnJianZhuangKuangId] text , " //该户有几个安检隐患
            + "[TaskListEntity_id] text , " //这个是任务列表的外键
            + "[state] text , " // 安检等级  也叫整改状态
            + "[PaigongNumber] text , " // 安检等级
            + "[AddressName] text, "   //地址名称  后来加上去的


            + "[SP_Id] text , "   //计划编号
            + "[YonghuId] text , "
//            安检隐患等级 0表示无 1高 2中 3低
            + "[RectificationGrade] TEXT, "
            + "[AnJianZhuangTai] text, "
            + "[category] VARCHAR(50), "
            // LeiBie 默认为0  1表示已安检 2表示已上传 3驳回的数据 4审核成功 5从前的数据
            + "[LeiBie] VARCHAR(50) DEFAULT '0', "
//            选择上传中 标志是否选中  选中是1  已上传为2
            + "[DanTiao] VARCHAR(50), "
            + "[StopTime] VARCHAR(50), "
            + "[BoHuiLiYou] VARCHAR(50), "
            + "[UserId] VARCHAR(50), "
            + "[AnJianCiShu] VARCHAR(50), "
            + "[IT_Reject] VARCHAR(50), "      //这个是驳回的理由
            + "[IT_State] text DEFAULT '0', "    //0正常 1驳回的数据
            + "[AD_Name] text DEFAULT '', " //备份字段
            + "[AD_Phone] text DEFAULT '', " //备份字段
            + "[IT_NumberTime] text , " //备份字段
            + "[YongHuQianMian] text , " //备份字段
            + "[RanQiBiao] text , " //备份字段
            + "[ChuFang] text , " //备份字段
            + "[QiTaBeiZhu] text , " //备份字段
            + "[SP_Number] text , " //备份字段
            + "[RootState] text)";//区分安检的状态  1正常入户
    /*
   *    安检项  表建表语句
   *
   */
    public static final String CREATE_SecurityItemEntity = "create table SecurityItemEntity ("
            + "id integer NOT NULL primary key ,"//设置id是插入的，并且是唯一的
            + "edit text, "
            + "content text, "
            + "ST_Id text, "
            + "SN_Number text, "
            + "RectificationGrade text)";//次数
     /*
    *    photo表建表语句
    */
    public static final String CREATE_WORK = "create table work ("
            + "id integer NOT NULL primary key autoincrement,"//注释
            + "[data] VARCHAR(255), "
            + "[time] VARCHAR(255), "
            + "[function] VARCHAR(255), "
            + "[image_url] VARCHAR(255))";
    /*
    *    BasicData表建表语句
    */
    public static final String CREATE_IMAGE = "create table image ("
            + "id integer NOT NULL primary key autoincrement,"//注释 id 自增长
            + "[address] VARCHAR(255), "
            + "[workId] integer)";//次数
    /*
    *   安检状态表建表语句
    *    CREATE TABLE SecurityStatus (id INTEGER primary key autoincrement,RenWuId text,
    *    IT_Number text,AnJianXiangId text,AnJianMiaoShu text,AnJianZhaoPian text);
    */
    public static final String CREATE_SecurityStatus = "create table SecurityStatus ("
            + "id integer NOT NULL primary key autoincrement,"//注释 id 自增长
            + "RenWuId text, "
            + "IT_Number text, "
            + "AnJianXiangId text, "
            + "AnJianMiaoShu text, "
            + "AnJianZhaoPian text)";//次数

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
    /*
    *    其他备注表建表语句
    *    CREATE TABLE QiTaBeiZhu (id INTEGER primary key,[BeiZhuYu] text,[TuPian] text);
    */
    public static final String CREATE_QiTaBeiZhu = "create table QiTaBeiZhu ("
            + "id integer NOT NULL primary key autoincrement,"//注释 id 自增长
            + "[BeiZhuYu] text, "
            + "[TuPian] text)";//次数
     /*
    *    登录ip表建表语句
    *    CREATE TABLE BaseUrl (id INTEGER primary key,[BaseUrl] text);
    */
    public static final String CREATE_BaseUrl = "create table BaseUrl ("
            + "id integer NOT NULL primary key autoincrement,"//注释 id 自增长
            + "[BaseUrl] text)";//次数

    /*
    *    BasicData表建表语句  保存的数据是入户时拒绝入户和到访不遇的次数
    */
        public static final String CREATE_BASICDATA = "create table BasicData ("
                + "id integer NOT NULL primary key autoincrement,"//注释 id 自增长
                + "[RenWuId] intege, "
                + "[type] VARCHAR(100), "
                + "[frequency] integer)";//次数
}
