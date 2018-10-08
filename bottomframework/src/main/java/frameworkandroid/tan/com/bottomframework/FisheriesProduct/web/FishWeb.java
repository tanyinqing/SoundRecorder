package frameworkandroid.tan.com.bottomframework.FisheriesProduct.web;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.Advertisement;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.CategoryParent;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.CategoryTree;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.DatabaseDetail;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.NewsEventList;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.CategoryChildren;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.NewsDetailModel;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.SystemList;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity.User;
import frameworkandroid.tan.com.bottomframework.FisheriesProduct.impl.IFishWeb;
import frameworkandroid.tan.com.bottomframework.listener.DataListener;
import frameworkandroid.tan.com.bottomframework.util.AssetsUtils;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;
import frameworkandroid.tan.com.bottomframework.util.utils_log.LogTxt_json;
import frameworkandroid.tan.com.bottomframework.web.BaseWeb;


public class FishWeb extends BaseWeb implements IFishWeb {

    private static String OrderUrl = BaseUrl + "newsEvent/";

	/*http://192.168.10.124:8080/mDataCenter/main/newsEvent/list?offset=0&&count=20
    http://192.168.10.124:8080/newsEvent/list.shtml*/

    private Context context;
    private String loginUrl = OrderUrl + "login";
    private String newsEventListUrl = OrderUrl + "list.shtml";
    private String newsEventUrl = OrderUrl + "main/newsEvent";
    private String newsEvent1Url = OrderUrl + "main/newsEvent/";

    private static String OrderUrl1 = BaseUrl + "category/";
    private String findParentUrl = OrderUrl1 + "findParent.shtml";
    private String findChildrenUrl = OrderUrl1 + "findChildren.shtml";
    private String categoryTreeUrl = OrderUrl1 + "tree.shtml";

    private static String OrderUrl2 = BaseUrl + "system/";
    private String listUrl = OrderUrl2 + "list.shtml";

    private String DatabaseDetailUrl = BaseUrl + "/data/A0001/detail.shtml";

    public FishWeb(Context context) {
        super(context);
        this.context = context;
    }

    //	http://192.168.10.125/mDataCenter/main/newsEvent
//	http://192.168.10.125/mDataCenter/main/newsEvent/3
    //写的模拟的渔业动态详情的接口
    @Override
    public void newsEvent(String id, final DataListener<NewsDetailModel> dataListener) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("id", "id");
        post(newsEventUrl + "?id=" + 3, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //		post(Constant.PublicWebSite, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
                MyLog.ShowLog("返回来的数据" + json);
//				 json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/news_detail3.txt");
                LogTxt_json.writeLog(newsEventUrl + "\r\n" + "数据" + "\r\n" + json, "newsEvent", Constant.program_name);
                Type type = new TypeToken<NewsDetailModel>() {
                }.getType();
                parse(json, type, dataListener);
            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }

    //这个是登录的页面
    @Override
    public void login(String name, String password, final DataListener<User> dataListener) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("name", name);
        params.addBodyParameter("password", password);
        post(loginUrl, params, HttpRequest.HttpMethod.POST, new IRequestListener() {
            //		post(Constant.PublicWebSite, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
                //	MyLog.ShowLog(json);
//				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(loginUrl + "\r\n" + "数据" + "\r\n" + json, "login", Constant.program_name);
                Type type = new TypeToken<User>() {
                }.getType();
                parse(json, type, dataListener);
            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }

    //	http://192.168.10.125/mDataCenter/main/newsEvent?offset=0&&count=20
//	http://192.168.10.125/mDataCenter/main/newsEvent/list
    // 这个是列表的页面
    @Override
    public void newsEventList(String offset, String count, final DataListener<List<NewsEventList>> dataListener) {
        RequestParams params = new RequestParams();
		/*params.addBodyParameter("offset",offset);
		params.addBodyParameter("count", count);*/
        post(newsEventListUrl + "?offset=" + offset + "&&count=" + count, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //		post(Constant.PublicWebSite, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
                //	MyLog.ShowLog(json);
//				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(newsEventListUrl + "\r\n" + "数据" + "\r\n" + json, "newsEventList", Constant.program_name);

                Type type = new TypeToken<List<NewsEventList>>() {
                }.getType();
//				parse(json, type, dataListener);

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(json);//把字符串转化成一个json对象
//			if ("true".equals(jsonObject.getString("success"))) {
                    if ((jsonObject.getInt("code")) == 1) {
                        Gson gson = new Gson();
                        String data1 = jsonObject.getString("data");
//				MyLog.ShowLog("data1的值是"+data1);
//				String data2=(new JSONObject(data1)).getString("data");
                        if ("[]".equals(data1) || "[[]]".equals(data1)) {
//					PublicUtil.ShowToast("没有数据返回！");
                            dataListener.onSuccess(null);
                            return;
                        }
                        List<NewsEventList> obj = gson.fromJson(data1, type);
                        dataListener.onSuccess(obj);
                    } else {
				/*String data1=jsonObject.getString("errors");
				String data2=(new JSONObject(data1)).getString("message");*/
                        PublicUtil.ShowToast("" + jsonObject.getString("msg"));
                        dataListener.onFailed();
                    }
                } catch (Exception e) {
                    dataListener.onFailed();
                    PublicUtil.ShowToast("" + e.toString());
//					Log.e(TAG, "BaseWeb  parse失败: " + e.toString());
                    MyLog.ShowLog("BaseWeb  parse失败 : " + e.toString());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }

    //这个轮播图页面
    public void findAdvertisementList(String offset, String count, final DataListener<List<Advertisement>> dataListener) {
        RequestParams params = new RequestParams();
        //params.addBodyParameter("order_id",order_id);
        //	post(findAdvertisementListUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
        post(Constant.PublicWebSite, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
                //	MyLog.ShowLog(json);
                json = AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(Constant.PublicWebSite + "\r\n" + "数据" + "\r\n" + json, "findAdvertisementList", Constant.program_name);
                Type type = new TypeToken<List<Advertisement>>() {
                }.getType();
                parse(json, type, dataListener);
            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }

    // 第二种获取渔业详情的方法
//	http://192.168.10.125//mDataCenter/main/newsEvent/3
    public void newsEvent1(String id, final DataListener<String> dataListener) {
        RequestParams params = new RequestParams();
        //params.addBodyParameter("order_id",order_id);
        //	post(findAdvertisementListUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
        post(newsEvent1Url + id, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //		post(Constant.PublicWebSite, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
                //	MyLog.ShowLog(json);
//				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(newsEvent1Url + "\r\n" + "数据" + "\r\n" + json, "newsEvent1", Constant.program_name);
				/*Type type = new TypeToken<List<Advertisement>>() {
				}.getType();
				parse(json, type, dataListener);*/
                dataListener.onSuccess(json);
            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }

    public void findParent(final DataListener<List<CategoryParent>> dataListener) {
        RequestParams params = new RequestParams();
        //params.addBodyParameter("order_id",order_id);
        //	post(findAdvertisementListUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
        post(findParentUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //		post(Constant.PublicWebSite, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
                //	MyLog.ShowLog(json);
//				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(findParentUrl + "\r\n" + "数据" + "\r\n" + json, "findParent", Constant.program_name);
                Type type = new TypeToken<List<CategoryParent>>() {
                }.getType();
                parse(json, type, dataListener);

            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }

    public void findChildren(String parent, final DataListener<List<CategoryChildren>> dataListener) {
        RequestParams params = new RequestParams();
        //params.addBodyParameter("order_id",order_id);
        post(findChildrenUrl + "?parent=" + parent, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
                //	MyLog.ShowLog(json);
//				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(findChildrenUrl + "\r\n" + "数据" + "\r\n" + json, "findChildrenUrl", Constant.program_name);
                Type type = new TypeToken<List<CategoryChildren>>() {
                }.getType();
                parse(json, type, dataListener);

            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }

    public void list(String parent, final DataListener<List<SystemList>> dataListener) {
        RequestParams params = new RequestParams();
        //params.addBodyParameter("order_id",order_id);
        post(BaseUrl + parent, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //		post(listUrl+"?id="+parent, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //	post("http://192.168.10.124:8080/data/A0001.shtml?page=1&limit=30", params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
                MyLog.ShowLog(json);
//				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(listUrl + "\r\n" + "数据" + "\r\n" + json, "listUrl", Constant.program_name);
                Type type = new TypeToken<List<SystemList>>() {
                }.getType();
                parse(json, type, dataListener);
            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }


    public void DatabaseDetail(int id, final DataListener<List<DatabaseDetail>> dataListener) {
        RequestParams params = new RequestParams();
        //params.addBodyParameter("order_id",order_id);
        post(DatabaseDetailUrl + "?id=" + id, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //		post(listUrl+"?id="+parent, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //	post("http://192.168.10.124:8080/data/A0001.shtml?page=1&limit=30", params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
//				MyLog.ShowLog(json);
//				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(DatabaseDetailUrl + "\r\n" + "数据" + "\r\n" + json, "DatabaseDetailUrl", Constant.program_name);
                Type type = new TypeToken<List<DatabaseDetail>>() {
                }.getType();
                parse(json, type, dataListener);
            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }

    public void categoryTree(final DataListener<List<CategoryTree>> dataListener) {
        RequestParams params = new RequestParams();
        //params.addBodyParameter("order_id",order_id);
        post(categoryTreeUrl, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //		post(listUrl+"?id="+parent, params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            //	post("http://192.168.10.124:8080/data/A0001.shtml?page=1&limit=30", params, HttpRequest.HttpMethod.GET, new IRequestListener() {
            @Override
            public void onSuccess(String json) {
//				MyLog.ShowLog(json);
//				json= AssetsUtils.getAssetsData(context, "FisheriesProduct/user/findAdvertisementList.txt");
                LogTxt_json.writeLog(categoryTreeUrl + "\r\n" + "数据" + "\r\n" + json, "categoryTreeUrl", Constant.program_name);
                Type type = new TypeToken<List<CategoryTree>>() {
                }.getType();
                parse(json, type, dataListener);
            }

            @Override
            public void onFailed() {
                dataListener.onFailed();
            }
        });
    }
}
