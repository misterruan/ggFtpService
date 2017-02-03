package com.ytx.util.handler;

import com.ytx.model.CustomerLimitPrice;
import com.ytx.model.CustomerPosition;
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
 * 客户限价单sax解析处理类
 */
public class CustomerLimitPriceHandler extends DefaultHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomerLimitPriceHandler.class);

    private List<CustomerLimitPrice> customerLimitPriceList = null;
    private CustomerLimitPrice customerLimitPrice = null;
    //作用是记录解析时的上一个节点名称
    private String preTag = null;
    //记录行数
    private int count = 0;

    public List<CustomerLimitPrice> getCustomerLimitPriceList(InputStream xmlStream) throws Exception{
        SAXParser saxParser = SaxUtil.getSAXParser();
        CustomerLimitPriceHandler handler = new CustomerLimitPriceHandler();
        saxParser.parse(xmlStream, handler);
        return handler.getCustomerLimitPriceList();
    }

    private List<CustomerLimitPrice> getCustomerLimitPriceList(){
        return this.customerLimitPriceList;
    }

    @Override
    public void startDocument() throws SAXException {
        customerLimitPriceList = new LinkedList<CustomerLimitPrice>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if ("Row".equals(qName)) {
            count++;
            customerLimitPrice = new CustomerLimitPrice();
        }
        //将正在解析的节点名称赋给preTag
        preTag = qName;
    }


    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if ("Row".equals(qName)) {
            customerLimitPriceList.add(customerLimitPrice);
            customerLimitPrice = null;
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
                customerLimitPrice.setLoginaccount(content);
            } else if ("customername".equals(preTag)) {
                customerLimitPrice.setCustomername(content);
            } else if ("limitorderid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerLimitPrice.setLimitorderid(bigDecimal);
            } else if ("commoditycode".equals(preTag)) {
                customerLimitPrice.setCommoditycode(content);
            } else if ("opendirector".equals(preTag)) {
                customerLimitPrice.setOpendirector(content);
            } else if ("openqty".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerLimitPrice.setOpenqty(bigDecimal);
            } else if ("orderprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerLimitPrice.setOrderprice(bigDecimal);
            } else if ("tpprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerLimitPrice.setTpprice(bigDecimal);
            } else if ("slprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerLimitPrice.setSlprice(bigDecimal);
            } else if ("expiretype".equals(preTag)) {
                customerLimitPrice.setExpiretype(content);
            }else if ("createdate".equals(preTag)) {
                customerLimitPrice.setCreatedate(DateUtil.string2Date(content));
            }else if ("updatedate".equals(preTag)) {
                customerLimitPrice.setUpdatedate(DateUtil.string2Date(content));
            }else if ("dealstatus".equals(preTag)) {
                customerLimitPrice.setDealstatus(content);
            }else if ("id2".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerLimitPrice.setId2(bigDecimal);
            }else if ("userid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerLimitPrice.setUserid(bigDecimal);
            }else if ("relateid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerLimitPrice.setRelateid(bigDecimal);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("CustomerLimitPriceHandler finished -> parse "+count+" row total。");
    }


}
