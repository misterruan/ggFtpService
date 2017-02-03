package com.ytx.util.handler;

import com.ytx.model.CustomerBankRoll;
import com.ytx.util.DateUtil;
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
 * 客户资金流水sax解析处理类
 */
public class CustomerBankRollHandler extends DefaultHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomerBankRollHandler.class);

    private List<CustomerBankRoll> customerBankRollList = null;
    private CustomerBankRoll customerBankRoll = null;
    //作用是记录解析时的上一个节点名称
    private String preTag = null;
    //记录行数
    private int count = 0;

    public List<CustomerBankRoll> getCustomerBankRollList(InputStream xmlStream) throws Exception{
        SAXParser saxParser = SaxUtil.getSAXParser();
        CustomerBankRollHandler handler = new CustomerBankRollHandler();
        saxParser.parse(xmlStream, handler);
        return handler.getCustomerBankRollList();
    }

    private List<CustomerBankRoll> getCustomerBankRollList(){
        return this.customerBankRollList;
    }

    @Override
    public void startDocument() throws SAXException {
        customerBankRollList = new LinkedList<CustomerBankRoll>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if ("Row".equals(qName)) {
            count++;
            customerBankRoll = new CustomerBankRoll();
        }
        //将正在解析的节点名称赋给preTag
        preTag = qName;
    }


    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if ("Row".equals(qName)) {
            customerBankRollList.add(customerBankRoll);
            customerBankRoll = null;
        }
        preTag = null;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (preTag != null) {
            String content = new String(ch, start, length).trim();
            BigDecimal bigDecimal = null;
            if ("chglogid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerBankRoll.setChglogid(bigDecimal);
            } else if ("loginaccount".equals(preTag)) {
                customerBankRoll.setLoginaccount(content);
            } else if ("customername".equals(preTag)) {
                customerBankRoll.setCustomername(content);
            } else if ("changetype".equals(preTag)) {
                customerBankRoll.setChangetype(content);
            } else if ("beforeamount".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerBankRoll.setBeforeamount(bigDecimal);
            } else if ("amount".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerBankRoll.setAmount(bigDecimal);
            } else if ("afteramount".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerBankRoll.setAfteramount(bigDecimal);
            } else if ("createdate".equals(preTag)) {
                customerBankRoll.setCreatedate(DateUtil.string2Date(content));
            } else if ("id2".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerBankRoll.setId2(bigDecimal);
            } else if ("userid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerBankRoll.setUserid(bigDecimal);
            }

        }
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("CustomerBankRollHandler finished -> parse "+count+" row total。");
    }


}
