package com.ytx;

import com.ytx.constant.DataImportLogConstant;
import com.ytx.model.DataImportLog;
import com.ytx.service.DataLogService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

/**
 * Created by rock on 2017/1/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class DataLogTest  extends TestCase {

    @Autowired
    private DataLogService dataLogService;

    @Test
    public void test(){
        //正常
//        dataLogService.createDataLog2(createDataImportLog("bbb.txt"));
        //有异常
//        dataLogService.createDataLog(createDataImportLog("aaa.txt"));
        dataLogService.updateDataLogStatusAndExceptionMessage("3","11",null);
    }

    private DataImportLog createDataImportLog(String fileName){
        DataImportLog dataImportLog = new DataImportLog();
        dataImportLog.setId(UUID.randomUUID().toString());
        dataImportLog.setFileName(fileName);
        dataImportLog.setStatus(DataImportLogConstant.STATUS_BEGIN);
        dataImportLog.setCteateTime(new Date());
        dataImportLog.setUpdateTime(new Date());
        return dataImportLog;
    }

}
