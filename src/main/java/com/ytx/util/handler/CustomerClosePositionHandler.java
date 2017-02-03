package com.ytx.util.handler;

import com.ytx.model.CustomerClosePosition;
import com.ytx.model.CustomerLimitPrice;
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
 * 客户平仓单sax解析处理类
 */
public class CustomerClosePositionHandler extends DefaultHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomerClosePositionHandler.class);

    private List<CustomerClosePosition> customerClosePositionList = null;
    private CustomerClosePosition customerClosePosition = null;
    //作用是记录解析时的上一个节点名称
    private String preTag = null;
    //记录行数
    private int count = 0;

    public List<CustomerClosePosition> getCustomerClosePositionList(InputStream xmlStream) throws Exception{
        SAXParser saxParser = SaxUtil.getSAXParser();
        CustomerClosePositionHandler handler = new CustomerClosePositionHandler();
        saxParser.parse(xmlStream, handler);
        return handler.getCustomerClosePositionList();
    }

    private List<CustomerClosePosition> getCustomerClosePositionList(){
        return this.customerClosePositionList;
    }

    @Override
    public void startDocument() throws SAXException {
        customerClosePositionList = new LinkedList<CustomerClosePosition>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if ("Row".equals(qName)) {
            count++;
            customerClosePosition = new CustomerClosePosition();
        }
        //将正在解析的节点名称赋给preTag
        preTag = qName;
    }


    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if ("Row".equals(qName)) {
            customerClosePositionList.add(customerClosePosition);
            customerClosePosition = null;
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
                customerClosePosition.setLoginaccount(content);
            } else if ("customername".equals(preTag)) {
                customerClosePosition.setCustomername(content);
            } else if ("commoditycode".equals(preTag)) {
                customerClosePosition.setCommoditycode(content);
            } else if ("holderid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setHolderid(bigDecimal);
            } else if ("holdprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setHoldprice(bigDecimal);
            } else if ("closeid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setCloseid(bigDecimal);
            } else if ("closedirector".equals(preTag)) {
                customerClosePosition.setClosedirector(content);
            } else if ("closetime".equals(preTag)) {
                customerClosePosition.setClosetime(DateUtil.string2Date(content));
            } else if ("closeprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setCloseprice(bigDecimal);
            } else if ("traderange".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setTraderange(bigDecimal);
            }else if ("closeqty".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setCloseqty(bigDecimal);
            }else if ("closepl".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setClosepl(bigDecimal);
            }else if ("charge".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setCharge(bigDecimal);
            }else if ("latefee".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setLatefee(bigDecimal);
            }else if ("openid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setOpenid(bigDecimal);
            }else if ("opendirector".equals(preTag)) {
                customerClosePosition.setOpendirector(content);
            }else if ("opentime".equals(preTag)) {
                customerClosePosition.setOpentime(DateUtil.string2Date(content));
            }else if ("userid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerClosePosition.setUserid(bigDecimal);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("CustomerClosePositionHandler finished -> parse "+count+" row total。");
    }


}
