package com.ytx.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rock on 2016/12/27.
 * 客户平仓单（xml对应model）
 */
public class CustomerClosePosition {
    //客户登录ID
    private String loginaccount;
    //姓名
    private String customername;
    //商品代码
    private String commoditycode;
    //持仓单号
    private BigDecimal holderid;
    //持仓价
    private BigDecimal holdprice;
    //平仓单号
    private BigDecimal closeid;
    //平仓方向
    private String closedirector;
    //平仓时间
    private Date closetime;
    //平仓价
    private BigDecimal closeprice;
    //允许成交点差
    private BigDecimal traderange;
    //数量
    private BigDecimal closeqty;
    //平仓盈亏
    private BigDecimal closepl;
    //手续费
    private BigDecimal charge;
    //滞纳金
    private BigDecimal latefee;
    //建仓单号
    private BigDecimal openid;
    //建仓方向
    private String opendirector;
    //建仓时间
    private Date opentime;
    //用户标识
    private BigDecimal userid;

    @Override
    public String toString() {
        return "CustomerClosePosition{" +
                "loginaccount='" + loginaccount + '\'' +
                ", customername='" + customername + '\'' +
                ", commoditycode='" + commoditycode + '\'' +
                ", holderid=" + holderid +
                ", holdprice=" + holdprice +
                ", closeid=" + closeid +
                ", closedirector='" + closedirector + '\'' +
                ", closetime=" + closetime +
                ", closeprice=" + closeprice +
                ", traderange=" + traderange +
                ", closeqty=" + closeqty +
                ", closepl=" + closepl +
                ", charge=" + charge +
                ", latefee=" + latefee +
                ", openid=" + openid +
                ", opendirector='" + opendirector + '\'' +
                ", opentime=" + opentime +
                ", userid=" + userid +
                '}';
    }

    public String getLoginaccount() {
        return loginaccount;
    }

    public void setLoginaccount(String loginaccount) {
        this.loginaccount = loginaccount;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCommoditycode() {
        return commoditycode;
    }

    public void setCommoditycode(String commoditycode) {
        this.commoditycode = commoditycode;
    }

    public BigDecimal getHolderid() {
        return holderid;
    }

    public void setHolderid(BigDecimal holderid) {
        this.holderid = holderid;
    }

    public BigDecimal getHoldprice() {
        return holdprice;
    }

    public void setHoldprice(BigDecimal holdprice) {
        this.holdprice = holdprice;
    }

    public BigDecimal getCloseid() {
        return closeid;
    }

    public void setCloseid(BigDecimal closeid) {
        this.closeid = closeid;
    }

    public String getClosedirector() {
        return closedirector;
    }

    public void setClosedirector(String closedirector) {
        this.closedirector = closedirector;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public BigDecimal getCloseprice() {
        return closeprice;
    }

    public void setCloseprice(BigDecimal closeprice) {
        this.closeprice = closeprice;
    }

    public BigDecimal getTraderange() {
        return traderange;
    }

    public void setTraderange(BigDecimal traderange) {
        this.traderange = traderange;
    }

    public BigDecimal getCloseqty() {
        return closeqty;
    }

    public void setCloseqty(BigDecimal closeqty) {
        this.closeqty = closeqty;
    }

    public BigDecimal getClosepl() {
        return closepl;
    }

    public void setClosepl(BigDecimal closepl) {
        this.closepl = closepl;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getLatefee() {
        return latefee;
    }

    public void setLatefee(BigDecimal latefee) {
        this.latefee = latefee;
    }

    public BigDecimal getOpenid() {
        return openid;
    }

    public void setOpenid(BigDecimal openid) {
        this.openid = openid;
    }

    public String getOpendirector() {
        return opendirector;
    }

    public void setOpendirector(String opendirector) {
        this.opendirector = opendirector;
    }

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }
}
