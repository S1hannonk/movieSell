package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName("orders")
public class Orders {
    @TableId("order_no")
    private String orderNo;
    @TableField("order_time")
    private Date orderTime;
    @TableField("order_user_id")
    private Integer orderUserId;
    @TableField("order_user_nick")
    private String orderUserNick;
    @TableField("order_cinema_id")
    private Integer orderCinemaId;
    @TableField("order_cinema_name")
    private String orderCinemaName;
    @TableField("order_film_id")
    private Integer orderFilmId;
    @TableField("order_film_name")
    private String orderFilmName;
    @TableField("order_wd_date")
    private String orderWdDate;
    @TableField("order_wt_begintime")
    private String orderWtBegintime;
    @TableField("order_wt_endtime")
    private String orderWtEndtime;
    @TableField("order_wt_halls")
    private String orderWtHalls;
    @TableField("order_cost")
    private BigDecimal orderCost;
    @TableField("order_sites")
    private String orderSites;
    @TableField("order_wt_id")
    private String orderWtId;
    @TableField("order_is_use")
    private Byte orderIsUse;
    @TableField("order_state")
    private Byte orderState;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getOrderUserNick() {
        return orderUserNick;
    }

    public void setOrderUserNick(String orderUserNick) {
        this.orderUserNick = orderUserNick == null ? null : orderUserNick.trim();
    }

    public Integer getOrderCinemaId() {
        return orderCinemaId;
    }

    public void setOrderCinemaId(Integer orderCinemaId) {
        this.orderCinemaId = orderCinemaId;
    }

    public String getOrderCinemaName() {
        return orderCinemaName;
    }

    public void setOrderCinemaName(String orderCinemaName) {
        this.orderCinemaName = orderCinemaName == null ? null : orderCinemaName.trim();
    }

    public Integer getOrderFilmId() {
        return orderFilmId;
    }

    public void setOrderFilmId(Integer orderFilmId) {
        this.orderFilmId = orderFilmId;
    }

    public String getOrderFilmName() {
        return orderFilmName;
    }

    public void setOrderFilmName(String orderFilmName) {
        this.orderFilmName = orderFilmName == null ? null : orderFilmName.trim();
    }

    public String getOrderWdDate() {
        return orderWdDate;
    }

    public void setOrderWdDate(String orderWdDate) {
        this.orderWdDate = orderWdDate == null ? null : orderWdDate.trim();
    }

    public String getOrderWtBegintime() {
        return orderWtBegintime;
    }

    public void setOrderWtBegintime(String orderWtBegintime) {
        this.orderWtBegintime = orderWtBegintime == null ? null : orderWtBegintime.trim();
    }

    public String getOrderWtEndtime() {
        return orderWtEndtime;
    }

    public void setOrderWtEndtime(String orderWtEndtime) {
        this.orderWtEndtime = orderWtEndtime == null ? null : orderWtEndtime.trim();
    }

    public String getOrderWtHalls() {
        return orderWtHalls;
    }

    public void setOrderWtHalls(String orderWtHalls) {
        this.orderWtHalls = orderWtHalls == null ? null : orderWtHalls.trim();
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public String getOrderSites() {
        return orderSites;
    }

    public void setOrderSites(String orderSites) {
        this.orderSites = orderSites == null ? null : orderSites.trim();
    }

    public String getOrderWtId() {
        return orderWtId;
    }

    public void setOrderWtId(String orderWtId) {
        this.orderWtId = orderWtId == null ? null : orderWtId.trim();
    }

    public Byte getOrderIsUse() {
        return orderIsUse;
    }

    public void setOrderIsUse(Byte orderIsUse) {
        this.orderIsUse = orderIsUse;
    }

    public Byte getOrderState() {
        return orderState;
    }

    public void setOrderState(Byte orderState) {
        this.orderState = orderState;
    }
}