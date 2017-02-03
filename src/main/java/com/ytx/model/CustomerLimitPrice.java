package com.ytx.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rock on 2016/12/27.
 * 客户限价单（xml对应model）
 */
public class CustomerLimitPrice {
    //客户登录ID
    private String loginaccount;
    //姓名
    private String customername;
    //单号
    private BigDecimal limitorderid;
    //商品代码
    private String commoditycode;
    //买卖方向
    private String opendirector;
    //数量
    private BigDecimal openqty;
    //限价(委托价格)
    private BigDecimal orderprice;
    //止盈价
    private BigDecimal tpprice;
    //止损价
    private BigDecimal slprice;
    //期限
    private String expiretype;
    //下单时间
    private Date createdate;
    //成交/撤销时间
    private Date updatedate;
    //处理状态
    private String dealstatus;
    //成交单号
    private BigDecimal id2;
    //用户标识
    private BigDecimal userid;
    //关联单号
    private BigDecimal relateid;

    @Override
    public String toString() {
        return "CustomerLimitPrice{" +
                "loginaccount='" + loginaccount + '\'' +
                ", customername='" + customername + '\'' +
                ", limitorderid=" + limitorderid +
                ", commoditycode='" + commoditycode + '\'' +
                ", opendirector='" + opendirector + '\'' +
                ", openqty=" + openqty +
                ", orderprice=" + orderprice +
                ", tpprice=" + tpprice +
                ", slprice=" + slprice +
                ", expiretype='" + expiretype + '\'' +
                ", createdate=" + createdate +
                ", updatedate=" + updatedate +
                ", dealstatus='" + dealstatus + '\'' +
                ", id2=" + id2 +
                ", userid=" + userid +
                ", relateid=" + relateid +
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

    public BigDecimal getLimitorderid() {
        return limitorderid;
    }

    public void setLimitorderid(BigDecimal limitorderid) {
        this.limitorderid = limitorderid;
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

    public BigDecimal getOpenqty() {
        return openqty;
    }

    public void setOpenqty(BigDecimal openqty) {
        this.openqty = openqty;
    }

    public BigDecimal getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
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

    public String getExpiretype() {
        return expiretype;
    }

    public void setExpiretype(String expiretype) {
        this.expiretype = expiretype;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getDealstatus() {
        return dealstatus;
    }

    public void setDealstatus(String dealstatus) {
        this.dealstatus = dealstatus;
    }

    public BigDecimal getId2() {
        return id2;
    }

    public void setId2(BigDecimal id2) {
        this.id2 = id2;
    }

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public BigDecimal getRelateid() {
        return relateid;
    }

    public void setRelateid(BigDecimal relateid) {
        this.relateid = relateid;
    }
}
