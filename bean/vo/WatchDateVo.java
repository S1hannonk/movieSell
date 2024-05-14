package movieSell.bean.vo;

import movieSell.bean.po.WatchTimes;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName:
 *
 * @author: Shannon
 * description:观影日期试图模型类
 */
public class WatchDateVo {
    //观影日期
    private String date;
    //拍片场次列表
    private List<WatchTimes> watchTimesList = new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<WatchTimes> getWatchTimesList() {
        return watchTimesList;
    }

    public void setWatchTimesList(List<WatchTimes> watchTimesList) {
        this.watchTimesList = watchTimesList;
    }
}
