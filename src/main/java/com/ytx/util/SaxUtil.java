package com.ytx.util;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by rock on 2016/12/27.
 */
public class SaxUtil {

    public static SAXParser getSAXParser() throws ParserConfigurationException, SAXException {
        // step 1: 获得SAX解析器工厂实例
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // step 2: 获得SAX解析器实例
        return  factory.newSAXParser();
    }

}
