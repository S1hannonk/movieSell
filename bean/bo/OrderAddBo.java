package movieSell.bean.bo;

import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public class OrderAddBo {

    private String watchTimeId;// 订单场次
    private List<Site> sites; //订单座位

    public String getWatchTimeId() {
        return watchTimeId;
    }

    public void setWatchTimeId(String watchTimeId) {
        this.watchTimeId = watchTimeId;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}
