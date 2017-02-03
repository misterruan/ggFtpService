package com.ytx.model;

import java.math.BigDecimal;

/**
 * Created by rock on 2016/12/27.
 * 客户账户（xml对应model）
 */
public class CustomerAccount {
    //客户登录ID
    private String loginaccount;
    //姓名
    private String customername;
    //上日余额
    private BigDecimal lastamount;
    //今日余额
    private BigDecimal todayamount;
    //结算盈亏
    private BigDecimal reckonpl;
    //净值
    private BigDecimal netvalue;
    //交易准备金
    private BigDecimal remain_margin;
    //履约准备金
    private BigDecimal used_margin;
    //冻结准备金
    private BigDecimal freeze_margin;
    //风险率
    private BigDecimal riskratio;
    //当日出入金净值
    private BigDecimal amountinout;
    //用户标识
    private BigDecimal userid;
    //日入金累计
    private BigDecimal amountin;
    //日出金累计
    private BigDecimal amountout;

    @Override
    public String toString() {
        return "CustomerAccount{" +
                "loginaccount='" + loginaccount + '\'' +
                ", customername='" + customername + '\'' +
                ", lastamount=" + lastamount +
                ", todayamount=" + todayamount +
                ", reckonpl=" + reckonpl +
                ", netvalue=" + netvalue +
                ", remain_margin=" + remain_margin +
                ", used_margin=" + used_margin +
                ", freeze_margin=" + freeze_margin +
                ", riskratio=" + riskratio +
                ", amountinout=" + amountinout +
                ", userid=" + userid +
                ", amountin=" + amountin +
                ", amountout=" + amountout +
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

    public BigDecimal getLastamount() {
        return lastamount;
    }

    public void setLastamount(BigDecimal lastamount) {
        this.lastamount = lastamount;
    }

    public BigDecimal getTodayamount() {
        return todayamount;
    }

    public void setTodayamount(BigDecimal todayamount) {
        this.todayamount = todayamount;
    }

    public BigDecimal getReckonpl() {
        return reckonpl;
    }

    public void setReckonpl(BigDecimal reckonpl) {
        this.reckonpl = reckonpl;
    }

    public BigDecimal getNetvalue() {
        return netvalue;
    }

    public void setNetvalue(BigDecimal netvalue) {
        this.netvalue = netvalue;
    }

    public BigDecimal getRemain_margin() {
        return remain_margin;
    }

    public void setRemain_margin(BigDecimal remain_margin) {
        this.remain_margin = remain_margin;
    }

    public BigDecimal getUsed_margin() {
        return used_margin;
    }

    public void setUsed_margin(BigDecimal used_margin) {
        this.used_margin = used_margin;
    }

    public BigDecimal getFreeze_margin() {
        return freeze_margin;
    }

    public void setFreeze_margin(BigDecimal freeze_margin) {
        this.freeze_margin = freeze_margin;
    }

    public BigDecimal getRiskratio() {
        return riskratio;
    }

    public void setRiskratio(BigDecimal riskratio) {
        this.riskratio = riskratio;
    }

    public BigDecimal getAmountinout() {
        return amountinout;
    }

    public void setAmountinout(BigDecimal amountinout) {
        this.amountinout = amountinout;
    }

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public BigDecimal getAmountin() {
        return amountin;
    }

    public void setAmountin(BigDecimal amountin) {
        this.amountin = amountin;
    }

    public BigDecimal getAmountout() {
        return amountout;
    }

    public void setAmountout(BigDecimal amountout) {
        this.amountout = amountout;
    }
}
