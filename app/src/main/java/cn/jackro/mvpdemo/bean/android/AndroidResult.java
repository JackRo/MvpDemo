package cn.jackro.mvpdemo.bean.android;

import java.util.List;

/**
 * @author JackRo
 */
@SuppressWarnings("unused")
public class AndroidResult {

    /**
     * _id : 5a5a0c63421aa911577aa7c0
     * createdAt : 2018-01-13T21:40:51.632Z
     * desc : 简洁优雅可点击的toast控件，仿手机百度9.0，无BadTokenException风险。
     * images : ["http://img.gank.io/375cce8b-6398-48a9-8107-8ac970f8c672"]
     * publishedAt : 2018-01-16T08:40:08.101Z
     * source : chrome
     * type : Android
     * url : https://github.com/bboylin/UniversalToast
     * used : true
     * who : null
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
