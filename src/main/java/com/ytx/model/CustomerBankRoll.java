package com.ytx.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rock on 2016/12/27.
 * 客户资金流水（xml对应model）
 */
public class CustomerBankRoll {
    //流水号
    private BigDecimal chglogid;
    //客户登录ID
    private String loginaccount;
    //姓名
    private String customername;
    //类型
    private String changetype;
    //起始金额
    private BigDecimal beforeamount;
    //变动金额
    private BigDecimal amount;
    //变后金额
    private BigDecimal afteramount;
    //变更时间
    private Date createdate;
    //成交单号
    private BigDecimal id2;
    //用户标识
    private BigDecimal userid;

    @Override
    public String toString() {
        return "CustomerBankRoll{" +
                "chglogid=" + chglogid +
                ", loginaccount='" + loginaccount + '\'' +
                ", customername='" + customername + '\'' +
                ", changetype='" + changetype + '\'' +
                ", beforeamount=" + beforeamount +
                ", amount=" + amount +
                ", afteramount=" + afteramount +
                ", createdate=" + createdate +
                ", id2=" + id2 +
                ", userid=" + userid +
                '}';
    }

    public BigDecimal getChglogid() {
        return chglogid;
    }

    public void setChglogid(BigDecimal chglogid) {
        this.chglogid = chglogid;
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

    public String getChangetype() {
        return changetype;
    }

    public void setChangetype(String changetype) {
        this.changetype = changetype;
    }

    public BigDecimal getBeforeamount() {
        return beforeamount;
    }

    public void setBeforeamount(BigDecimal beforeamount) {
        this.beforeamount = beforeamount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAfteramount() {
        return afteramount;
    }

    public void setAfteramount(BigDecimal afteramount) {
        this.afteramount = afteramount;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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
}
