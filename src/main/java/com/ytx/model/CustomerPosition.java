package com.ytx.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rock on 2016/12/27.
 * 客户持仓（xml对应model）
 */
public class CustomerPosition {
    //客户登录ID
    private String loginaccount;
    //姓名
    private String customername;
    //单号
    private BigDecimal holderid;
    //数量
    private BigDecimal holdqty;
    //商品代码
    private String commoditycode;
    //买卖方向
    private String opendirector;
    //操作类型
    private String opentype;
    //操作员
    private BigDecimal creator_login_id;
    //建仓价
    private BigDecimal openprice;
    //持仓价
    private BigDecimal holderprice;
    //允许成交点差
    private BigDecimal traderange;
    //结算盈亏
    private BigDecimal settlementpl;
    //建仓时间
    private Date opentime;
    //手续费
    private BigDecimal charge;
    //滞纳金
    private BigDecimal latefee;
    //止盈价
    private BigDecimal tpprice;
    //止损价
    private BigDecimal slprice;
    //占用保证金
    private BigDecimal usedmargin;
    //用户标识
    private BigDecimal userid;
    //建仓量
    private BigDecimal openquantity;

    @Override
    public String toString() {
        return "CustomerPosition{" +
                "loginaccount='" + loginaccount + '\'' +
                ", customername='" + customername + '\'' +
                ", holderid=" + holderid +
                ", holdqty=" + holdqty +
                ", commoditycode='" + commoditycode + '\'' +
                ", opendirector='" + opendirector + '\'' +
                ", opentype='" + opentype + '\'' +
                ", creator_login_id=" + creator_login_id +
                ", openprice=" + openprice +
                ", holderprice=" + holderprice +
                ", traderange=" + traderange +
                ", settlementpl=" + settlementpl +
                ", opentime=" + opentime +
                ", charge=" + charge +
                ", latefee=" + latefee +
                ", tpprice=" + tpprice +
                ", slprice=" + slprice +
                ", usedmargin=" + usedmargin +
                ", userid=" + userid +
                ", openquantity=" + openquantity +
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

    public BigDecimal getHolderid() {
        return holderid;
    }

    public void setHolderid(BigDecimal holderid) {
        this.holderid = holderid;
    }

    public BigDecimal getHoldqty() {
        return holdqty;
    }

    public void setHoldqty(BigDecimal holdqty) {
        this.holdqty = holdqty;
    }

    public String getCommoditycode() {
        return commoditycode;
    }

    public void setCommoditycode(String commoditycode) {
        this.commoditycode = commoditycode;
    }

    public String getOpendirector() {
        return opendirector;
    }

    public void setOpendirector(String opendirector) {
        this.opendirector = opendirector;
    }

    public String getOpentype() {
        return opentype;
    }

    public void setOpentype(String opentype) {
        this.opentype = opentype;
    }

    public BigDecimal getCreator_login_id() {
        return creator_login_id;
    }

    public void setCreator_login_id(BigDecimal creator_login_id) {
        this.creator_login_id = creator_login_id;
    }

    public BigDecimal getOpenprice() {
        return openprice;
    }

    public void setOpenprice(BigDecimal openprice) {
        this.openprice = openprice;
    }

    public BigDecimal getHolderprice() {
        return holderprice;
    }

    public void setHolderprice(BigDecimal holderprice) {
        this.holderprice = holderprice;
    }

    public BigDecimal getTraderange() {
        return traderange;
    }

    public void setTraderange(BigDecimal traderange) {
        this.traderange = traderange;
    }

    public BigDecimal getSettlementpl() {
        return settlementpl;
    }

    public void setSettlementpl(BigDecimal settlementpl) {
        this.settlementpl = settlementpl;
    }

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
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

    public BigDecimal getTpprice() {
        return tpprice;
    }

    public void setTpprice(BigDecimal tpprice) {
        this.tpprice = tpprice;
    }

    public BigDecimal getSlprice() {
        return slprice;
    }

    public void setSlprice(BigDecimal slprice) {
        this.slprice = slprice;
    }

    public BigDecimal getUsedmargin() {
        return usedmargin;
    }

    public void setUsedmargin(BigDecimal usedmargin) {
        this.usedmargin = usedmargin;
    }

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public BigDecimal getOpenquantity() {
        return openquantity;
    }

    public void setOpenquantity(BigDecimal openquantity) {
        this.openquantity = openquantity;
    }
}
