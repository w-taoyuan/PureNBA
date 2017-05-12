package m520it.com.purenba.bean;

/**
 * 作者:张弘杰
 */

public class MatchInfoBean {

    private String columnId;
    private String icon;
    private String name;
    private String matchNum;
    private String matchDesc;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(String matchNum) {
        this.matchNum = matchNum;
    }

    public String getMatchDesc() {
        return matchDesc;
    }

    public void setMatchDesc(String matchDesc) {
        this.matchDesc = matchDesc;
    }

    @Override
    public String toString() {
        return "MatchInfoBean{" +
                "columnId='" + columnId + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", matchNum='" + matchNum + '\'' +
                ", matchDesc='" + matchDesc + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
