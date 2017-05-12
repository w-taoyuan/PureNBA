package m520it.com.purenba.fragment.headfrag.recommend;

/**
 * Created by conan on 2017/4/27.
 */

public class BannerMatchBean {

    private String matchType;
    private String leftName;
    private String rightName;
    private String leftBadge;
    private String rightBadge;
    private String startTime;
    private String matchDesc;
    private String leftGoal;
    private String rightGoal;
    private String quarter;
    private String quarterTime;

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getLeftName() {
        return leftName;
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getLeftBadge() {
        return leftBadge;
    }

    public void setLeftBadge(String leftBadge) {
        this.leftBadge = leftBadge;
    }

    public String getRightBadge() {
        return rightBadge;
    }

    public void setRightBadge(String rightBadge) {
        this.rightBadge = rightBadge;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getMatchDesc() {
        return matchDesc;
    }

    public void setMatchDesc(String matchDesc) {
        this.matchDesc = matchDesc;
    }

    public String getLeftGoal() {
        return leftGoal;
    }

    public void setLeftGoal(String leftGoal) {
        this.leftGoal = leftGoal;
    }

    public String getRightGoal() {
        return rightGoal;
    }

    public void setRightGoal(String rightGoal) {
        this.rightGoal = rightGoal;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getQuarterTime() {
        return quarterTime;
    }

    public void setQuarterTime(String quarterTime) {
        this.quarterTime = quarterTime;
    }

    @Override
    public String toString() {
        return "BannerMatchBean{" +
                "matchType='" + matchType + '\'' +
                ", leftName='" + leftName + '\'' +
                ", rightName='" + rightName + '\'' +
                ", leftBadge='" + leftBadge + '\'' +
                ", rightBadge='" + rightBadge + '\'' +
                ", startTime='" + startTime + '\'' +
                ", matchDesc='" + matchDesc + '\'' +
                ", leftGoal='" + leftGoal + '\'' +
                ", rightGoal='" + rightGoal + '\'' +
                ", quarter='" + quarter + '\'' +
                ", quarterTime='" + quarterTime + '\'' +
                '}';
    }
}
