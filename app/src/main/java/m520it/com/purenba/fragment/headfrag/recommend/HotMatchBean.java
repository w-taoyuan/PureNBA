package m520it.com.purenba.fragment.headfrag.recommend;

/**
 * Created by conan on 2017/4/28.
 */

public class HotMatchBean {

    private String title;
    private String secondTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    @Override
    public String toString() {
        return "HotMatchBean{" +
                "title='" + title + '\'' +
                ", secondTitle='" + secondTitle + '\'' +
                '}';
    }
}
