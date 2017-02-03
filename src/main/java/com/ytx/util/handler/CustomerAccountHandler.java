package com.ytx.util.handler;

import com.ytx.model.CustomerAccount;
import com.ytx.util.SaxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rock on 2016/12/27.
 * 客户资金账户sax解析处理类
 */
public class CustomerAccountHandler extends DefaultHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomerAccountHandler.class);

    private List<CustomerAccount> customerAccountList = null;
    private CustomerAccount customerAccount = null;
    //作用是记录解析时的上一个节点名称
    private String preTag = null;
    //记录行数
    private int count = 0;

    public List<CustomerAccount> getCustomerAccountList(InputStream xmlStream) throws Exception{
        SAXParser saxParser = SaxUtil.getSAXParser();
        CustomerAccountHandler handler = new CustomerAccountHandler();
        saxParser.parse(xmlStream, handler);
        //因为要拿到handler对象对应的customerAccountList，所以不能直接用return this.customerAccountList;
        return handler.getCustomerAccountList();
    }

    public List<CustomerAccount> getCustomerAccountList(){
        return this.customerAccountList;
    }

    @Override
    public void startDocument() throws SAXException {
        customerAccountList = new LinkedList<CustomerAccount>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if ("Row".equals(qName)) {
            count++;
            customerAccount = new CustomerAccount();
        }
        //将正在解析的节点名称赋给preTag
        preTag = qName;
    }


    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if ("Row".equals(qName)) {
            customerAccountList.add(customerAccount);
            customerAccount = null;
        }
        preTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (preTag != null) {
            String content = new String(ch, start, length).trim();
            BigDecimal bigDecimal = null;
            if ("loginaccount".equals(preTag)) {
                customerAccount.setLoginaccount(content);
            } else if ("customername".equals(preTag)) {
                customerAccount.setCustomername(content);
            } else if ("lastamount".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setLastamount(bigDecimal);
            } else if ("todayamount".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setTodayamount(bigDecimal);
            } else if ("reckonpl".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setReckonpl(bigDecimal);
            } else if ("netvalue".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setNetvalue(bigDecimal);
            } else if ("remain_margin".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setRemain_margin(bigDecimal);
            } else if ("used_margin".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setUsed_margin(bigDecimal);
            } else if ("freeze_margin".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setFreeze_margin(bigDecimal);
            } else if ("riskratio".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setRiskratio(bigDecimal);
            } else if ("amountinout".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setAmountinout(bigDecimal);
            } else if ("userid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setUserid(bigDecimal);
            } else if ("amountin".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setAmountin(bigDecimal);
            } else if ("amountout".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerAccount.setAmountout(bigDecimal);
            }

        }
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("CustomerAccountHandler finished -> parse "+count+" row total。");
    }


}
