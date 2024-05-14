package movieSell.bean.bo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public class SiteChooseBo {
    private String watchTimeId; //场次编号
    private Site site; //座位

    public String getWatchTimeId() {
        return watchTimeId;
    }

    public void setWatchTimeId(String watchTimeId) {
        this.watchTimeId = watchTimeId;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
