package com.ytx;

import com.ytx.controller.DataImportController;
import com.ytx.model.DataImportLog;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rock on 2017/1/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class ControllerTest extends TestCase {

    @Autowired
    private DataImportController dataImportController;

    @Test
    public void test1(){
        dataImportController.batchAddCustomerAccount();
    }

    @Test
    public void test2(){
        dataImportController.batchAddCustomerBankRoll();
    }

    @Test
    public void test3(){
        dataImportController.batchAddCustomerClosePosition();
    }

    @Test
    public void test4(){
        dataImportController.batchAddCustomerLimitPrice();
    }

    @Test
    public void test5(){
        dataImportController.batchAddCustomerPosition();
    }

    @Test
    public void test6(){
        dataImportController.generateFilesFromFtp();
    }

}
