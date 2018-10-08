package frameworkandroid.tan.com.bottomframework.FisheriesProduct.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class Images implements Serializable {
    private String alt;
    private String pixel;
    private String ref;
    private String src;

    public Images() {
    }

    @Override
    public String toString() {
        return "Images{" +
                "alt='" + alt + '\'' +
                ", pixel='" + pixel + '\'' +
                ", ref='" + ref + '\'' +
                ", src='" + src + '\'' +
                '}';
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

