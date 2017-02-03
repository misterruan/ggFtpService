package com.ytx.util.handler;

import com.ytx.model.CustomerBankRoll;
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
 * 客户资金流水sax解析处理类
 */
public class CustomerPositionHandler extends DefaultHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomerPositionHandler.class);

    private List<CustomerPosition> customerPositionList = null;
    private CustomerPosition customerPosition = null;
    //作用是记录解析时的上一个节点名称
    private String preTag = null;
    //记录行数
    private int count = 0;

    public List<CustomerPosition> getCustomerPositionList(InputStream xmlStream) throws Exception{
        SAXParser saxParser = SaxUtil.getSAXParser();
        CustomerPositionHandler handler = new CustomerPositionHandler();
        saxParser.parse(xmlStream, handler);
        return handler.getCustomerPositionList();
    }

    private List<CustomerPosition> getCustomerPositionList(){
        return this.customerPositionList;
    }

    @Override
    public void startDocument() throws SAXException {
        customerPositionList = new LinkedList<CustomerPosition>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if ("Row".equals(qName)) {
            count++;
            customerPosition = new CustomerPosition();
        }
        //将正在解析的节点名称赋给preTag
        preTag = qName;
    }


    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if ("Row".equals(qName)) {
            customerPositionList.add(customerPosition);
            customerPosition = null;
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
                customerPosition.setLoginaccount(content);
            } else if ("customername".equals(preTag)) {
                customerPosition.setCustomername(content);
            } else if ("holderid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setHolderid(bigDecimal);
            } else if ("holdqty".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setHoldqty(bigDecimal);
            } else if ("commoditycode".equals(preTag)) {
                customerPosition.setCommoditycode(content);
            } else if ("opendirector".equals(preTag)) {
                customerPosition.setOpendirector(content);
            } else if ("opentype".equals(preTag)) {
                customerPosition.setOpentype(content);
            } else if ("creator_login_id".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setCreator_login_id(bigDecimal);
            } else if ("openprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setOpenprice(bigDecimal);
            } else if ("holderprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setHolderprice(bigDecimal);
            }else if ("traderange".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setTraderange(bigDecimal);
            }else if ("settlementpl".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setSettlementpl(bigDecimal);
            }else if ("opentime".equals(preTag)) {
                customerPosition.setOpentime(DateUtil.string2Date(content));
            }else if ("charge".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setCharge(bigDecimal);
            }else if ("latefee".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setLatefee(bigDecimal);
            }else if ("tpprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setTpprice(bigDecimal);
            }else if ("slprice".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setSlprice(bigDecimal);
            }else if ("usedmargin".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setUsedmargin(bigDecimal);
            }else if ("userid".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setUserid(bigDecimal);
            }else if ("openquantity".equals(preTag)) {
                bigDecimal = new BigDecimal(content);
                customerPosition.setOpenquantity(bigDecimal);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("CustomerPositionHandler finished -> parse "+count+" row total。");
    }


}
