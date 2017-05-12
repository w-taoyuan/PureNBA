package m520it.com.purenba.bean;

/**
 * 作者:张弘杰
 */

public class ListinfoBean {

        private String categoryId;
        private String ifHasHighlights;
        private String ifHasPlayback;
        private String isDisabled;
        private String isPay;
        private String liveId;
        private String liveSource;
        private MatchBean matchInfo;

        private String updateFrequency;
        private String userNum;

        private String title;
        private String curMatchDay;
        private String lastMatchDay;
        private String nextMatchDay;

    public MatchBean getMatchInfo() {
        return matchInfo;
    }

    public void setMatchInfo(MatchBean matchInfo) {
        this.matchInfo = matchInfo;
    }

    public ListinfoBean() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getIfHasHighlights() {
        return ifHasHighlights;
    }

    public void setIfHasHighlights(String ifHasHighlights) {
        this.ifHasHighlights = ifHasHighlights;
    }

    public String getIfHasPlayback() {
        return ifHasPlayback;
    }

    public void setIfHasPlayback(String ifHasPlayback) {
        this.ifHasPlayback = ifHasPlayback;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getLiveSource() {
        return liveSource;
    }

    public void setLiveSource(String liveSource) {
        this.liveSource = liveSource;
    }

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurMatchDay() {
        return curMatchDay;
    }

    public void setCurMatchDay(String curMatchDay) {
        this.curMatchDay = curMatchDay;
    }

    public String getLastMatchDay() {
        return lastMatchDay;
    }

    public void setLastMatchDay(String lastMatchDay) {
        this.lastMatchDay = lastMatchDay;
    }

    public String getNextMatchDay() {
        return nextMatchDay;
    }

    public void setNextMatchDay(String nextMatchDay) {
        this.nextMatchDay = nextMatchDay;
    }

    @Override
    public String toString() {
        return "ListinfoBean{" +
                "categoryId='" + categoryId + '\'' +
                ", ifHasHighlights='" + ifHasHighlights + '\'' +
                ", ifHasPlayback='" + ifHasPlayback + '\'' +
                ", isDisabled='" + isDisabled + '\'' +
                ", isPay='" + isPay + '\'' +
                ", liveId='" + liveId + '\'' +
                ", liveSource='" + liveSource + '\'' +
                ", matchInfo=" + matchInfo +
                ", updateFrequency='" + updateFrequency + '\'' +
                ", userNum='" + userNum + '\'' +
                ", title='" + title + '\'' +
                ", curMatchDay='" + curMatchDay + '\'' +
                ", lastMatchDay='" + lastMatchDay + '\'' +
                ", nextMatchDay='" + nextMatchDay + '\'' +
                '}';
    }
}
