package com.ytx;

import com.ytx.service.DataImportService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by rock on 2016/10/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class SpringTest extends TestCase {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataImportService dataImportService;


    @Test
    public void testHandle() throws IOException {

    }





}
