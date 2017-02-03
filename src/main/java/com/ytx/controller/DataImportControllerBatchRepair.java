package com.ytx.controller;

import com.ytx.constant.DataImportLogConstant;
import com.ytx.model.*;
import com.ytx.service.DataImportService;
import com.ytx.service.DataLogService;
import com.ytx.service.SystemConfigService;
import com.ytx.util.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by rock on 2016/12/29.
 */
@Controller
public class DataImportControllerBatchRepair {
    @Autowired
    private DataImportService dataImportService;
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private DataLogService dataLogService;


    private Logger logger = LoggerFactory.getLogger(DataImportControllerBatchRepair.class);

    @Scheduled(cron="0 35 10 * * ?")
    public void batchAddCustomerAccount1(){
        for (int i = 5; i < 6; i++) {
            batchAddCustomerAccount(getDate(i));
        }
    }
    @Scheduled(cron="0 35 10 * * ?")
    public void batchAddCustomerAccount2(){
        for (int i = 5; i < 6; i++) {
            batchAddCustomerBankRoll(getDate(i));
        }
    }
    @Scheduled(cron="0 35 10 * * ?")
    public void batchAddCustomerAccount3(){
        for (int i = 5; i < 6; i++) {
            batchAddCustomerClosePosition(getDate(i));
        }
    }
    @Scheduled(cron="0 35 10 * * ?")
    public void batchAddCustomerAccount4(){
        for (int i = 5; i < 6; i++) {
            batchAddCustomerLimitPrice(getDate(i));
        }
    }
    @Scheduled(cron="0 35 10 * * ?")
    public void batchAddCustomerAccount5(){
        for (int i = 5; i < 6; i++) {
            batchAddCustomerPosition(getDate(i));
        }
    }





    /**
     * 1.客户账户
     */
    public void batchAddCustomerAccount(String getDate) {
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户账户_"+getDate+".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator  = System.getProperty("file.separator");
        File file = new File(localFileDirectory+file_separator+fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        if(!file.exists()){
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
            return;
        }
        List<CustomerAccount> customerAccountList = null;
        try {
            customerAccountList = new CustomerAccountHandler().getCustomerAccountList(new FileInputStream(file));
            dataImportService.batchAddCustomerAccount(customerAccountList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("batchAddCustomerAccount meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END,dataImportLog.getId(),e.getMessage());
        }
    }

    /**
     * 2.客户出入金/客户资金流水信息
     */
    public void batchAddCustomerBankRoll(String getDate) {
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户资金流水_"+getDate+".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator  = System.getProperty("file.separator");
        File file = new File(localFileDirectory+file_separator+fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        if(!file.exists()){
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
            return;
        }
        List<CustomerBankRoll>  customerBankRollList = null;
        try {
            customerBankRollList = new CustomerBankRollHandler().getCustomerBankRollList(new FileInputStream(file));
            dataImportService.batchAddCustomerBankRoll(customerBankRollList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("batchAddCustomerBankRoll meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END,dataImportLog.getId(),e.getMessage());

        }
    }

    /**
     * 3.客户平仓单
     */
    public void batchAddCustomerClosePosition(String getDate) {
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户平仓单_"+getDate+".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator  = System.getProperty("file.separator");
        File file = new File(localFileDirectory+file_separator+fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        if(!file.exists()){
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
            return;
        }
        List<CustomerClosePosition>  customerClosePositionList  = null;
        try {
           customerClosePositionList = new CustomerClosePositionHandler().getCustomerClosePositionList(new FileInputStream(file));
            dataImportService.batchAddCustomerClosePosition(customerClosePositionList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("batchAddCustomerClosePosition meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END,dataImportLog.getId(),e.getMessage());

        }
    }

    /**
     * 4.客户限价单
     */
    public void batchAddCustomerLimitPrice(String getDate) {
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户限价单_"+getDate+".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator  = System.getProperty("file.separator");
        File file = new File(localFileDirectory+file_separator+fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        if(!file.exists()){
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
            return;
        }
        List<CustomerLimitPrice>  customerLimitPriceList = null;
        try {
            customerLimitPriceList = new CustomerLimitPriceHandler().getCustomerLimitPriceList(new FileInputStream(file));
            dataImportService.batchAddCustomerLimitPrice(customerLimitPriceList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("batchAddCustomerLimitPrice meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END,dataImportLog.getId(),e.getMessage());

        }
    }

    /**
     * 5.客户持仓
     */
    public void batchAddCustomerPosition(String getDate) {
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户持仓单_"+getDate+".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator  = System.getProperty("file.separator");
        File file = new File(localFileDirectory+file_separator+fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        if(!file.exists()){
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
            return;
        }
        List<CustomerPosition>  customerPositionList  = null;
        try {
            customerPositionList = new CustomerPositionHandler().getCustomerPositionList(new FileInputStream(file));
            dataImportService.batchAddCustomerPosition(customerPositionList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END,dataImportLog.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("batchAddCustomerPosition meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END,dataImportLog.getId(),e.getMessage());

        }
    }


    private DataImportLog createDataImportLog(String fileName){
        DataImportLog dataImportLog = new DataImportLog();
        dataImportLog.setId(UUID.randomUUID().toString());
        dataImportLog.setFileName(fileName);
        dataImportLog.setStatus(DataImportLogConstant.STATUS_BEGIN);
        dataImportLog.setCteateTime(new Date());
        dataImportLog.setUpdateTime(new Date());
        dataImportLog.setLogType(DataImportLogConstant.LOG_TYPE_PARSE_FILE);
        return dataImportLog;
    }

    private String getDate(int i){
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        return format.format(new Date());
        String ii = null;
        if(i<10){
            ii = "0"+i;
        }else{
            ii =i+"";
        }
        return "201701"+ii;
    }



}
