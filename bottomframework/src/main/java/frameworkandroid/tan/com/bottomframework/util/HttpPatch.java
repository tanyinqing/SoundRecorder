package frameworkandroid.tan.com.bottomframework.util;

import org.apache.http.client.methods.HttpPut;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
public class HttpPatch extends HttpPut {
    public HttpPatch(String url) {
        super(url);
    }
    @Override
    public String getMethod() {
        return "PATCH";
    }
}
