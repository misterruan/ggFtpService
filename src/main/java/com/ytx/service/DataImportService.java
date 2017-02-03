package com.ytx.service;

import com.ytx.dao.BatchDataImportDao;
import com.ytx.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Created by rock on 2016/11/10.
 */
@Service
@Transactional
public class DataImportService {
    @Autowired
    private BatchDataImportDao batchDataImportDao;
    //批量插入每批处理条数
    private static final int BUFFER_SIZE = 1000;


    /**
     * 客户账户
     * @param customerAccountList
     * @throws IOException
     */
    public void batchAddCustomerAccount(List<CustomerAccount> customerAccountList) throws IOException {
        if (customerAccountList!= null && customerAccountList.size() > BUFFER_SIZE) {
            do {
                batchDataImportDao.batchAddCustomerAccount(customerAccountList.subList(0, BUFFER_SIZE));
                customerAccountList.subList(0, BUFFER_SIZE).clear();
            } while (customerAccountList.size() > BUFFER_SIZE);
            //分批后剩余部分
            if (!customerAccountList.isEmpty()) {
                batchDataImportDao.batchAddCustomerAccount(customerAccountList);
            }
        } else {
            batchDataImportDao.batchAddCustomerAccount(customerAccountList);
        }
    }

    /**
     * 客户出入金/客户资金流水信息
     * @param statisticList
     * @throws IOException
     */
    public void batchAddCustomerBankRoll(List<CustomerBankRoll> statisticList) throws IOException {
        if (statisticList!= null && statisticList.size() > BUFFER_SIZE) {
            do {
                batchDataImportDao.batchAddCustomerBankRoll(statisticList.subList(0, BUFFER_SIZE));
                statisticList.subList(0, BUFFER_SIZE).clear();
            } while (statisticList.size() > BUFFER_SIZE);
            //分批后剩余部分
            if (!statisticList.isEmpty()) {
                batchDataImportDao.batchAddCustomerBankRoll(statisticList);
            }
        } else {
            batchDataImportDao.batchAddCustomerBankRoll(statisticList);
        }
    }

    /**
     * 客户平仓单
     * @param statisticList
     * @throws IOException
     */
    public void batchAddCustomerClosePosition(List<CustomerClosePosition> statisticList) throws IOException {
        if (statisticList!= null && statisticList.size() > BUFFER_SIZE) {
            do {
                batchDataImportDao.batchAddCustomerClosePosition(statisticList.subList(0, BUFFER_SIZE));
                statisticList.subList(0, BUFFER_SIZE).clear();
            } while (statisticList.size() > BUFFER_SIZE);
            //分批后剩余部分
            if (!statisticList.isEmpty()) {
                batchDataImportDao.batchAddCustomerClosePosition(statisticList);
            }
        } else {
            batchDataImportDao.batchAddCustomerClosePosition(statisticList);
        }
    }

    /**
     * 客户限价单
     * @param statisticList
     * @throws IOException
     */
    public void batchAddCustomerLimitPrice(List<CustomerLimitPrice> statisticList) throws IOException {
        if (statisticList!= null && statisticList.size() > BUFFER_SIZE) {
            do {
                batchDataImportDao.batchAddCustomerLimitPrice(statisticList.subList(0, BUFFER_SIZE));
                statisticList.subList(0, BUFFER_SIZE).clear();
            } while (statisticList.size() > BUFFER_SIZE);
            //分批后剩余部分
            if (!statisticList.isEmpty()) {
                batchDataImportDao.batchAddCustomerLimitPrice(statisticList);
            }
        } else {
            batchDataImportDao.batchAddCustomerLimitPrice(statisticList);
        }
    }

    /**
     * 客户持仓
     * @param statisticList
     * @throws IOException
     */
    public void batchAddCustomerPosition(List<CustomerPosition> statisticList) throws IOException {
        if (statisticList!= null && statisticList.size() > BUFFER_SIZE) {
            do {
                batchDataImportDao.batchAddCustomerPosition(statisticList.subList(0, BUFFER_SIZE));
                statisticList.subList(0, BUFFER_SIZE).clear();
            } while (statisticList.size() > BUFFER_SIZE);
            //分批后剩余部分
            if (!statisticList.isEmpty()) {
                batchDataImportDao.batchAddCustomerPosition(statisticList);
            }
        } else {
            batchDataImportDao.batchAddCustomerPosition(statisticList);
        }
    }


}
