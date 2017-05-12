package m520it.com.purenba.activity.channel;

import java.io.Serializable;

/**
 * Created by conan on 2017/4/25.
 */
public class ChannelBean implements Serializable{

    private String title;
    private int imgId;

    public ChannelBean(String title, int imgId) {
        this.title = title;
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public String toString() {
        return "ChannelBean{" +
                "title='" + title + '\'' +
                ", imgId=" + imgId +
                '}';
    }
}
