package com.ytx.dao;

import com.ytx.model.*;
import com.ytx.service.SystemConfigService;
import com.ytx.util.BusinessUtils;
import com.ytx.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by rock on 2016/10/14.
 * 将远程接口查询的数据批量导入本地库
 * 使用的是spring jdbc批处理，效率比单条插入高一个量级
 */
@Repository
public class BatchDataImportDao {

    @Autowired
    private SystemConfigService systemConfigService;
    private static Logger log = LoggerFactory.getLogger(BatchDataImportDao.class);

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 客户账户
     * @param customerAccountList
     */
    public void batchAddCustomerAccount(final List<CustomerAccount> customerAccountList) {
        String sql = "insert into t_FdsiPushCustomerInOutMoney (customeraccountid,customername,inoutamount,operatetime,yesterdaybalance,todaybalance,floatingprofitandloss," +
                "networth,exchangereserve,performancereserve,frozenreserve,riskratio,userid,amountin,amountout) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int index) throws SQLException {
                CustomerAccount customerAccount = customerAccountList.get(index);
                ps.setString(1,customerAccount.getLoginaccount());
                ps.setString(2,customerAccount.getCustomername());
                ps.setBigDecimal(3,customerAccount.getAmountinout());
                //操作时间
                ps.setBigDecimal(4, BigDecimal.valueOf(new Date().getTime()));
                ps.setBigDecimal(5, customerAccount.getLastamount());
                ps.setBigDecimal(6, customerAccount.getTodayamount());
                ps.setBigDecimal(7, customerAccount.getReckonpl());
                ps.setBigDecimal(8, customerAccount.getNetvalue());
                ps.setBigDecimal(9, customerAccount.getRemain_margin());
                ps.setBigDecimal(10, customerAccount.getUsed_margin());
                ps.setBigDecimal(11, customerAccount.getFreeze_margin());
                ps.setBigDecimal(12, customerAccount.getRiskratio());
                ps.setBigDecimal(13, customerAccount.getUserid());
                ps.setBigDecimal(14, customerAccount.getAmountin());
                ps.setBigDecimal(15, customerAccount.getAmountout());
            }
            //批处理总条数
            public int getBatchSize() {
                return customerAccountList.size();
            }
        });
    }

    /**
     * 客户出入金/客户资金流水信息
     * @param statisticList
     */
    public void batchAddCustomerBankRoll(final List<CustomerBankRoll> statisticList) {
        final String sql = "insert into t_FdsiQueryCustomerCashFlow (customeraccountid,customername,flowid,businessorderid,changetime," +
                "changedmoney,changeingmoney,startmoney,changetype,updatetime,userid) values(?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CustomerBankRoll statistic = statisticList.get(i);
                ps.setString(1,statistic.getLoginaccount());
                ps.setString(2,statistic.getCustomername());
                ps.setString(3,String.valueOf(statistic.getChglogid()));
                ps.setString(4,String.valueOf(statistic.getId2()));
                ps.setBigDecimal(5, BigDecimal.valueOf(statistic.getCreatedate().getTime()));
                ps.setBigDecimal(6,statistic.getAfteramount());
                ps.setBigDecimal(7,statistic.getAmount());
                ps.setBigDecimal(8,statistic.getBeforeamount());
                //客户账户变动的类型中文,原对应字段：type
                ps.setString(9,statistic.getChangetype());
                //操作时间
                ps.setTimestamp(10,new java.sql.Timestamp(new Date().getTime()));
                ps.setBigDecimal(11,statistic.getUserid());
            }
            public int getBatchSize() {
                return statisticList.size();
            }
        });
    }

    /**
     * 客户平仓单
     * @param statisticList
     */
    public void batchAddCustomerClosePosition(final List<CustomerClosePosition> statisticList) {
        String sql = "insert into t_FdsiPushCustomerClosePosition (customeraccountid,customername,goodsid,positionorderid,holdprice," +
                "closeorderid,closedirection,closedate,closeprice,permitbusinessspread,num,closeprofitandloss,poundage,latefee,openid,opendirector,opentime,userid,commoditycode)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
           public void setValues(PreparedStatement ps, int i) throws SQLException {
               CustomerClosePosition statistic = statisticList.get(i);
               ps.setString(1,statistic.getLoginaccount());
               ps.setString(2,statistic.getCustomername());
               //由于数据库中用存储过程限制了本表和t_FdsiGoodsInfo表关联，导致goodsid不能为null，所以本次查询无效空串
               ps.setString(3,systemConfigService.getProperty(("gg.commoditycode."+statistic.getCommoditycode()),StringUtil.getEmptyString()));
               ps.setString(4,String.valueOf(statistic.getHolderid()));
               ps.setBigDecimal(5,statistic.getHoldprice());
               ps.setString(6,String.valueOf(statistic.getCloseid()));
               //买卖方向中文转数字
               ps.setInt(7, BusinessUtils.buyOrSellDictionary(statistic.getClosedirector()));
               ps.setBigDecimal(8, BigDecimal.valueOf(statistic.getClosetime().getTime()));
               ps.setBigDecimal(9,statistic.getCloseprice());
               ps.setBigDecimal(10,statistic.getTraderange());
               ps.setBigDecimal(11,statistic.getCloseqty());
               ps.setBigDecimal(12,statistic.getClosepl());
               ps.setBigDecimal(13,statistic.getCharge());
               ps.setBigDecimal(14,statistic.getLatefee());
               ps.setBigDecimal(15,statistic.getOpenid());
               ps.setInt(16,BusinessUtils.buyOrSellDictionary(statistic.getOpendirector()));
               ps.setBigDecimal(17, BigDecimal.valueOf(statistic.getOpentime().getTime()));
               ps.setBigDecimal(18,statistic.getUserid());
               //商品代码,原对应字段：goodsid
               ps.setString(19,statistic.getCommoditycode());
           }
           public int getBatchSize() {
               return statisticList.size();
           }
       });
    }

    /**
     * 客户限价单
     * @param statisticList
     */
    public void batchAddCustomerLimitPrice(final List<CustomerLimitPrice> statisticList) {
        String sql = "insert into t_FdsiPushCustomerLimitPrice (customeraccountid,orderid,goodsid,saledirection,num," +
                "limitprice,stopsurplusprice,stoplossprice,expiretype,placeorderdate,canceldate,relateid,completeid,updatetime,dealstatus,customername,userid,commoditycode) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CustomerLimitPrice statistic = statisticList.get(i);
                ps.setString(1,statistic.getLoginaccount());
                ps.setString(2,String.valueOf(statistic.getLimitorderid()));
                //由于数据库中用存储过程限制了本表和t_FdsiGoodsInfo表关联，导致goodsid不能为null，所以本次查询无效空串
                //商品代码,原对应字段：goodsid
                ps.setString(3,systemConfigService.getProperty(("gg.commoditycode."+statistic.getCommoditycode()),StringUtil.getEmptyString()));
                ps.setInt(4,BusinessUtils.buyOrSellDictionary(statistic.getOpendirector()));
                ps.setInt(5,Integer.valueOf(statistic.getOpenqty().toString()));
                ps.setBigDecimal(6,statistic.getOrderprice());
                ps.setBigDecimal(7,statistic.getTpprice());
                ps.setBigDecimal(8,statistic.getSlprice());
                ps.setString(9,statistic.getExpiretype());
                ps.setBigDecimal(10,BigDecimal.valueOf(statistic.getCreatedate().getTime()));
                ps.setBigDecimal(11,BigDecimal.valueOf(statistic.getUpdatedate().getTime()));
                ps.setString(12,String.valueOf(statistic.getRelateid()));
                ps.setString(13,String.valueOf(statistic.getId2()));
                ps.setTimestamp(14,new java.sql.Timestamp(new Date().getTime()));
                ps.setString(15,statistic.getDealstatus());
                ps.setString(16,statistic.getCustomername());
                ps.setBigDecimal(17,statistic.getUserid());
                //期限expiretype,对应原字段timelimit
                ps.setString(18,statistic.getCommoditycode());
            }
            public int getBatchSize() {
                return statisticList.size();
            }
        });
    }

    /**
     * 客户持仓
     * @param statisticList
     */
    public void batchAddCustomerPosition(final List<CustomerPosition> statisticList) {
        String sql = "insert into t_FdsiPushCustomerHoldPosition (customeraccountid,customername,orderid,goodsid,commoditycode,saledirection,num,handletype,handler,openprice,holdprice," +
                "permitholdspread,floatingprofitandloss,opendate,poundage,latefee,stopsurplusprice,stoplossprice,occupythemargin,userid,openquantity) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CustomerPosition statistic = statisticList.get(i);
                ps.setString(1,statistic.getLoginaccount());
                ps.setString(2,statistic.getCustomername());
                ps.setString(3,String.valueOf(statistic.getHolderid()));
                //由于数据库中用存储过程限制了本表和t_FdsiGoodsInfo表关联，导致goodsid不能为null，所以本次查询无效空串
                ps.setString(4,systemConfigService.getProperty(("gg.commoditycode."+statistic.getCommoditycode()),StringUtil.getEmptyString()));
                //商品代码,原对应字段：goodsid
                ps.setString(5,statistic.getCommoditycode());
                ps.setInt(6,BusinessUtils.buyOrSellDictionary(statistic.getOpendirector()));
                ps.setInt(7,Integer.valueOf(statistic.getHoldqty().toString()));
                //handletype,opentype 操作类型，中文转换
                BigDecimal value;
                if(systemConfigService.getProperty("gg.opentype."+statistic.getOpentype())==null){
                    value =null;
                }else{
                    value = new BigDecimal(systemConfigService.getProperty("gg.opentype."+statistic.getOpentype()));
                }
                ps.setBigDecimal(8,value);
                ps.setString(9,String.valueOf(statistic.getCreator_login_id()));
                ps.setBigDecimal(10,statistic.getOpenprice());
                ps.setBigDecimal(11,statistic.getHolderprice());
                ps.setBigDecimal(12,statistic.getTraderange());
                ps.setBigDecimal(13,statistic.getSettlementpl());
                ps.setBigDecimal(14,BigDecimal.valueOf(statistic.getOpentime().getTime()));
                ps.setBigDecimal(15,statistic.getCharge());
                ps.setBigDecimal(16,statistic.getLatefee());
                ps.setBigDecimal(17,statistic.getTpprice());
                ps.setBigDecimal(18,statistic.getSlprice());
                ps.setBigDecimal(19,statistic.getUsedmargin());
                ps.setBigDecimal(20,statistic.getUserid());
                ps.setBigDecimal(21,statistic.getOpenquantity());
            }
            public int getBatchSize() {
                return statisticList.size();
            }
        });
    }







}
