package com.ytx.constant;

/**
 * finish
 * Created by rock on 2016/10/21.
 */
public class DataImportLogConstant {

    /**
     * 定时任务创建状态
     */
    public static final String STATUS_BEGIN = "1";

    /**
     * 定时任务正常结束状态
     */
    public static final String STATUS_NORMAL_END = "2";

    /**
     * 定时任务非正常结束状态
     */
    public static final String STATUS_OFF_NORMAL_END = "3";


    /**
     * 1：从ftp下载文件日志类型
     */
    public static final String LOG_TYPE_FTP_DOWNLOAD = "1";

    /**
     * 2：解析文件入库日志类型
     */
    public static final String LOG_TYPE_PARSE_FILE = "2";


}
