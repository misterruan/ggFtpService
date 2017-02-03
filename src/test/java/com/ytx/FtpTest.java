package com.ytx;

import com.ytx.service.SystemConfigService;
import com.ytx.util.DownloadFileByFtpUtils;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by rock on 2016/12/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class FtpTest extends TestCase {

    @Autowired
    private SystemConfigService systemConfigService;
    /**
     * 将FTP服务器上文件下载到本地
     *
     */
    @Test
    public void testDownFile() {
        try {
            List<String> fileNameList = new ArrayList<String>();
            fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户平仓单_20140929.xml");
            fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户持仓单_20141006.xml");
            fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户资金流水_20141002.xml");
            fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户账户_20141217.xml");
            fileNameList.add("003_广东金祥银瑞贵金属经营有限公司_客户限价单_20141006.xml");


            boolean flag = DownloadFileByFtpUtils.downFile(systemConfigService.getProperty("ftp.remot.ip"), Integer.valueOf(systemConfigService.getProperty("ftp.remot.port")), systemConfigService.getProperty("ftp.remot.userName"),
                    systemConfigService.getProperty("ftp.remot.passWord"), systemConfigService.getProperty("ftp.remot.fileDirectory"), fileNameList, systemConfigService.getProperty("ftp.local.fileDirectory"));
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    @Test
    public void test(){
        System.out.println(getDate());
    }

}
