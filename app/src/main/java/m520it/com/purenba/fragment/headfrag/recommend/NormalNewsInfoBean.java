package m520it.com.purenba.fragment.headfrag.recommend;

import static m520it.com.purenba.R.id.news_type;

/**
 * Created by conan on 2017/4/27.
 */

public class NormalNewsInfoBean {

    private String title;
    private String atype;
    private String tag_key;
    private String commentsNum;
    private String imgurl;
    private String url;
    private String newsAppId;

    public String getNewsAppId() {
        return newsAppId;
    }

    public void setNewsAppId(String newsAppId) {
        this.newsAppId = newsAppId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag_key() {
        return tag_key;
    }

    public void setTag_key(String tag_key) {
        this.tag_key = tag_key;
    }

    public String getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(String commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public String toString() {
        return "NormalNewsInfoBean{" +
                "title='" + title + '\'' +
                ", atype='" + atype + '\'' +
                ", tag_key='" + tag_key + '\'' +
                ", commentsNum='" + commentsNum + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", url='" + url + '\'' +
                ", newsAppId='" + newsAppId + '\'' +
                '}';
    }
}
