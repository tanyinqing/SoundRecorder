package frameworkandroid.tan.com.bottomframework.entity_warehouse;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
public class PhotoValue implements Serializable {
    private String url;
    private String name;
    private String set_uri;
    private String hash;


    @Override
    public String toString() {
        return "PhotoValue{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", set_uri='" + set_uri + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSet_uri() {
        return set_uri;
    }

    public void setSet_uri(String set_uri) {
        this.set_uri = set_uri;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }


}
