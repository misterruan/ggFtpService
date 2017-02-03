package com.ytx;

import com.ytx.model.*;
import com.ytx.service.DataImportService;
import com.ytx.util.handler.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.List;

/**
 * Created by rock on 2016/12/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class parseXmlTest  extends TestCase {

    @Autowired
    private DataImportService dataImportService;

    @Test
    public void praseCustomerAccount() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("003_广东金祥银瑞贵金属经营有限公司_客户账户_20141217.xml");
        List<CustomerAccount> customerAccountList = new CustomerAccountHandler().getCustomerAccountList(inputStream);
//        for (CustomerAccount customerAccount : customerAccountList) {
//            System.out.println(customerAccount.toString());
//
//        }
        dataImportService.batchAddCustomerAccount(customerAccountList);
    }

    @Test
    public void praseCustomerBankRoll() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("003_广东金祥银瑞贵金属经营有限公司_客户资金流水_20141002.xml");
        List<CustomerBankRoll>  customerBankRollList = new CustomerBankRollHandler().getCustomerBankRollList(inputStream);
//        for (CustomerBankRoll customerBankRoll : customerBankRollList) {
//            System.out.println(customerBankRoll.toString());
//
//        }
        dataImportService.batchAddCustomerBankRoll(customerBankRollList);
    }

    @Test
    public void praseCustomerPosition() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("003_广东金祥银瑞贵金属经营有限公司_客户持仓单_20141217.xml");
        List<CustomerPosition>  customerPositionList = new CustomerPositionHandler().getCustomerPositionList(inputStream);
//        for (CustomerPosition customerPosition : customerPositionList) {
//            System.out.println(customerPosition.toString());
//
//        }
        dataImportService.batchAddCustomerPosition(customerPositionList);
    }

    @Test
    public void praseCustomerLimitPrice() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("003_广东金祥银瑞贵金属经营有限公司_客户限价单_20141006.xml");
        List<CustomerLimitPrice>  customerLimitPriceList = new CustomerLimitPriceHandler().getCustomerLimitPriceList(inputStream);
//        for (CustomerLimitPrice customerLimitPrice : customerLimitPriceList) {
//            System.out.println(customerLimitPrice.toString());
//
//        }
        dataImportService.batchAddCustomerLimitPrice(customerLimitPriceList);
    }


    @Test
    public void praseCustomerClosePosition() throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("003_广东金祥银瑞贵金属经营有限公司_客户平仓单_20140929.xml");
        List<CustomerClosePosition>  customerClosePositionList = new CustomerClosePositionHandler().getCustomerClosePositionList(inputStream);
//        for (CustomerClosePosition customerClosePosition : customerClosePositionList) {
//            System.out.println(customerClosePosition.toString());
//
//        }
        dataImportService.batchAddCustomerClosePosition(customerClosePositionList);
    }

}
