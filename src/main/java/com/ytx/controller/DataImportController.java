package com.ytx.controller;

import com.ytx.constant.DataImportLogConstant;
import com.ytx.model.*;
import com.ytx.service.DataImportService;
import com.ytx.service.DataLogService;
import com.ytx.service.SystemConfigService;
import com.ytx.util.DownloadFileByFtpUtils;
import com.ytx.util.StringUtil;
import com.ytx.util.handler.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.TestExecutionListeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rock on 2016/12/29.
 */
@Controller
public class DataImportController {
    @Autowired
    private DataImportService dataImportService;
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private DataLogService dataLogService;


    private Logger logger = LoggerFactory.getLogger(DataImportController.class);


    /**
     * 定时从ftp获取文件到本地目录
     * 需要确认对方生成到ftp的时间
     */
    public void generateFilesFromFtp() {
        List<String> fileNameList = new ArrayList();
        fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户账户_" + getDate() + ".xml");
        fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户资金流水_" + getDate() + ".xml");
        fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户平仓单_" + getDate() + ".xml");
        fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户限价单_" + getDate() + ".xml");
        fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户持仓单_" + getDate() + ".xml");
        logger.info("generateFilesFromFtp start");
        String allFileNames = StringUtil.getEmptyString();
        for (String fileName: fileNameList) {
            allFileNames += (fileName+",");
        }
        DataImportLog dataImportLog = createDataImportLog(allFileNames, DataImportLogConstant.LOG_TYPE_FTP_DOWNLOAD);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        try {
            boolean flag = DownloadFileByFtpUtils.downFile(systemConfigService.getProperty("ftp.remot.ip"), Integer.valueOf(systemConfigService.getProperty("ftp.remot.port")), systemConfigService.getProperty("ftp.remot.userName"),
                    systemConfigService.getProperty("ftp.remot.passWord"), systemConfigService.getProperty("ftp.remot.fileDirectory"), fileNameList, systemConfigService.getProperty("ftp.local.fileDirectory"));
            //修改log记录
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
        } catch (Exception e) {
            logger.error("generateFilesFromFtp meet error, fileNames:   {}", new Object[]{allFileNames, e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), e.getMessage());
        }
    }


    /**
     * 1.客户账户
     */
    public void batchAddCustomerAccount() {
        logger.info("batchAddCustomerAccount start");
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户账户_" + getDate() + ".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator = System.getProperty("file.separator");
        File file = new File(localFileDirectory + file_separator + fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName, DataImportLogConstant.LOG_TYPE_PARSE_FILE);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        List<CustomerAccount> customerAccountList = null;
        if (!file.exists()) {
            if (theDayBeforeIsWeekend()) {
                //文件不存在，是周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
            } else {
                //文件不存在，非周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), "本地目录找不到对应文件");
            }
            return;
        }
        try {
            customerAccountList = new CustomerAccountHandler().getCustomerAccountList(new FileInputStream(file));
            dataImportService.batchAddCustomerAccount(customerAccountList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
        } catch (Exception e) {
            logger.error("batchAddCustomerAccount meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), e.getMessage());
        }
    }

    /**
     * 2.客户出入金/客户资金流水信息
     */
    public void batchAddCustomerBankRoll() {
        logger.info("batchAddCustomerBankRoll start");
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户资金流水_" + getDate() + ".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator = System.getProperty("file.separator");
        File file = new File(localFileDirectory + file_separator + fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName, DataImportLogConstant.LOG_TYPE_PARSE_FILE);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        List<CustomerBankRoll> customerBankRollList = null;
        if (!file.exists()) {
            if (theDayBeforeIsWeekend()) {
                //文件不存在，是周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
            } else {
                //文件不存在，非周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), "本地目录找不到对应文件");
            }
            return;
        }
        try {
            customerBankRollList = new CustomerBankRollHandler().getCustomerBankRollList(new FileInputStream(file));
            dataImportService.batchAddCustomerBankRoll(customerBankRollList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
        } catch (Exception e) {
            logger.error("batchAddCustomerBankRoll meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), e.getMessage());
        }
    }

    /**
     * 3.客户平仓单
     */
    public void batchAddCustomerClosePosition() {
        logger.info("batchAddCustomerClosePosition start");
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户平仓单_" + getDate() + ".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator = System.getProperty("file.separator");
        File file = new File(localFileDirectory + file_separator + fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName, DataImportLogConstant.LOG_TYPE_PARSE_FILE);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        List<CustomerClosePosition> customerClosePositionList = null;
        if (!file.exists()) {
            if (theDayBeforeIsWeekend()) {
                //文件不存在，是周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
            } else {
                //文件不存在，非周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), "本地目录找不到对应文件");
            }
            return;
        }
        try {
            customerClosePositionList = new CustomerClosePositionHandler().getCustomerClosePositionList(new FileInputStream(file));
            dataImportService.batchAddCustomerClosePosition(customerClosePositionList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
        } catch (Exception e) {
            logger.error("batchAddCustomerClosePosition meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), e.getMessage());
        }
    }

    /**
     * 4.客户限价单
     */
    public void batchAddCustomerLimitPrice() {
        logger.info("batchAddCustomerLimitPrice start");
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户限价单_" + getDate() + ".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator = System.getProperty("file.separator");
        File file = new File(localFileDirectory + file_separator + fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName, DataImportLogConstant.LOG_TYPE_PARSE_FILE);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        List<CustomerLimitPrice> customerLimitPriceList = null;
        if (!file.exists()) {
            if (theDayBeforeIsWeekend()) {
                //文件不存在，是周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
            } else {
                //文件不存在，非周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), "本地目录找不到对应文件");
            }
            return;
        }
        try {
            customerLimitPriceList = new CustomerLimitPriceHandler().getCustomerLimitPriceList(new FileInputStream(file));
            dataImportService.batchAddCustomerLimitPrice(customerLimitPriceList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
        } catch (Exception e) {
            logger.error("batchAddCustomerLimitPrice meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), e.getMessage());
        }
    }

    /**
     * 5.客户持仓
     */
    public void batchAddCustomerPosition() {
        logger.info("batchAddCustomerPosition start");
        String fileName = "003_广东金祥银瑞贵金属经营有限公司_客户持仓单_" + getDate() + ".xml";
        String localFileDirectory = systemConfigService.getProperty("ftp.local.fileDirectory");
        String file_separator = System.getProperty("file.separator");
        File file = new File(localFileDirectory + file_separator + fileName);
        DataImportLog dataImportLog = createDataImportLog(fileName, DataImportLogConstant.LOG_TYPE_PARSE_FILE);
        //记录数据库
        dataLogService.createDataLog(dataImportLog);
        List<CustomerPosition> customerPositionList = null;
        if (!file.exists()) {
            if (theDayBeforeIsWeekend()) {
                //文件不存在，是周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
            } else {
                //文件不存在，非周末
                dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), "本地目录找不到对应文件");
            }
            return;
        }
        try {
            customerPositionList = new CustomerPositionHandler().getCustomerPositionList(new FileInputStream(file));
            dataImportService.batchAddCustomerPosition(customerPositionList);
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_NORMAL_END, dataImportLog.getId(), null);
        } catch (Exception e) {
            logger.error("batchAddCustomerPosition meet error,", new Object[]{e});
            dataLogService.updateDataLogStatusAndExceptionMessage(DataImportLogConstant.STATUS_OFF_NORMAL_END, dataImportLog.getId(), e.getMessage());
        }
    }


    private DataImportLog createDataImportLog(String fileName, String logType) {
        DataImportLog dataImportLog = new DataImportLog();
        dataImportLog.setId(UUID.randomUUID().toString());
        dataImportLog.setFileName(fileName);
        dataImportLog.setStatus(DataImportLogConstant.STATUS_BEGIN);
        dataImportLog.setCteateTime(new Date());
        dataImportLog.setUpdateTime(new Date());
        dataImportLog.setLogType(logType);
        return dataImportLog;
    }

    //昨天日期
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return  format.format(new Date(new Date().getTime() - 1 * 24 * 60 * 60 * 1000));
    }

    /**
     * 用于判断前一天是否是周末（周六或者周日）
     *
     * @return
     */
    private boolean theDayBeforeIsWeekend() {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        //星期天或星期一。星期天是1，类推
        if (today == 1 || today == 2) {
            return true;
        } else {
            return false;
        }
    }

}
