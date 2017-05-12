package m520it.com.purenba.bean;

/**
 * 作者:张弘杰
 */

public class MathInParseTempBean {

    private String columns;
    private String title;


    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MathInParseTempBean{" +
                "columns='" + columns + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
