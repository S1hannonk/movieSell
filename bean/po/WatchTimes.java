package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;

@TableName("watch_times")
public class WatchTimes {
    @TableId("wt_id")
    private String wtId;
    @TableField("wt_begintime")
    private String wtBegintime;
    @TableField("wt_endtime")
    private String wtEndtime;
    @TableField("wt_version")
    private String wtVersion;
    @TableField("wt_cost")
    private BigDecimal wtCost;
    @TableField("wt_maxsel")
    private Byte wtMaxsel;
    @TableField("film_id")
    private Integer filmId;
    @TableField("wd_date")
    private String wdDate;
    @TableField("cma_id")
    private Integer cmaId;
    @TableField("wt_halls")
    private String wtHalls;
    @TableField("wt_enable")
    private Byte wtEnable;
    @TableField("wt_url")
    private String wtUrl;
    @TableField("wt_cost_sites")
    private String wtCostSites;

    public String getWtId() {
        return wtId;
    }

    public void setWtId(String wtId) {
        this.wtId = wtId == null ? null : wtId.trim();
    }

    public String getWtBegintime() {
        return wtBegintime;
    }

    public void setWtBegintime(String wtBegintime) {
        this.wtBegintime = wtBegintime == null ? null : wtBegintime.trim();
    }

    public String getWtEndtime() {
        return wtEndtime;
    }

    public void setWtEndtime(String wtEndtime) {
        this.wtEndtime = wtEndtime == null ? null : wtEndtime.trim();
    }

    public String getWtVersion() {
        return wtVersion;
    }

    public void setWtVersion(String wtVersion) {
        this.wtVersion = wtVersion == null ? null : wtVersion.trim();
    }

    public BigDecimal getWtCost() {
        return wtCost;
    }

    public void setWtCost(BigDecimal wtCost) {
        this.wtCost = wtCost;
    }

    public Byte getWtMaxsel() {
        return wtMaxsel;
    }

    public void setWtMaxsel(Byte wtMaxsel) {
        this.wtMaxsel = wtMaxsel;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getWdDate() {
        return wdDate;
    }

    public void setWdDate(String wdDate) {
        this.wdDate = wdDate == null ? null : wdDate.trim();
    }

    public Integer getCmaId() {
        return cmaId;
    }

    public void setCmaId(Integer cmaId) {
        this.cmaId = cmaId;
    }

    public String getWtHalls() {
        return wtHalls;
    }

    public void setWtHalls(String wtHalls) {
        this.wtHalls = wtHalls == null ? null : wtHalls.trim();
    }

    public Byte getWtEnable() {
        return wtEnable;
    }

    public void setWtEnable(Byte wtEnable) {
        this.wtEnable = wtEnable;
    }

    public String getWtUrl() {
        return wtUrl;
    }

    public void setWtUrl(String wtUrl) {
        this.wtUrl = wtUrl == null ? null : wtUrl.trim();
    }

    public String getWtCostSites() {
        return wtCostSites;
    }

    public void setWtCostSites(String wtCostSites) {
        this.wtCostSites = wtCostSites == null ? null : wtCostSites.trim();
    }
}