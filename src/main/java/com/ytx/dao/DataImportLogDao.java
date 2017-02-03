package com.ytx.dao;

import com.ytx.model.DataImportLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by rock on 2016/10/21.
 * 定时任务执行状态日志记录
 */
@Repository
public class DataImportLogDao {
    private static Logger log = LoggerFactory.getLogger(DataImportLogDao.class);

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * 插入新的log记录
     * @param dataImportLog
     */
    public void createDataLog(DataImportLog dataImportLog) {
        String sql = "insert into t_FdsiGgFtpDataLog (id,fileName,status,cteateTime,updateTime,exceptionMessage,logType) values(?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, dataImportLog.getId(),dataImportLog.getFileName(),dataImportLog.getStatus(),dataImportLog.getCteateTime()
        ,dataImportLog.getUpdateTime(),dataImportLog.getExceptionMessage(),dataImportLog.getLogType());
    }
    /**
     * 通过id更新log的状态
     * @param dataLogStatus
     * @param id
     */
    public void updateDataLogStatusAndExceptionMessage(String dataLogStatus,String id,String exceptionMessage) {
        if(!StringUtils.isEmpty(dataLogStatus) ){
            String sql = "update t_FdsiGgFtpDataLog set updateTime=?,status=?,exceptionMessage=? where id=?";
            this.jdbcTemplate.update(sql, new Date(),dataLogStatus,exceptionMessage,id);
        }
    }
    /**
     * 通过id查找log对象
     * @param id
     * @return
     */
    public DataImportLog getDataLogById(String id) {
        String sql = "select * from t_FdsiGgFtpDataLog where id=?";
        return this.jdbcTemplate.queryForObject(sql, new DataImportLogRowMapper(), id);
    }

    /**
     * 查询执行失败的日志记录
     * @return
     */
    public List<DataImportLog> getFailureLogList() {
        String sql = "select * from t_FdsiGgFtpDataLog where status= '3'";
        return this.jdbcTemplate.query(sql,new DataImportLogRowMapper());
    }



    class DataImportLogRowMapper implements RowMapper<DataImportLog> {
        //rs为返回结果集，以每行为单位封装着
        public DataImportLog mapRow(ResultSet rs, int rowNum) throws SQLException {
            DataImportLog dataImportLog = new DataImportLog();
            dataImportLog.setId(rs.getString("id"));
            dataImportLog.setFileName(rs.getString("fileName"));
            dataImportLog.setStatus(rs.getString("status"));
            dataImportLog.setCteateTime(rs.getTimestamp("cteateTime"));
            dataImportLog.setUpdateTime(rs.getTimestamp("updateTime"));
            dataImportLog.setExceptionMessage(rs.getString("exceptionMessage"));
            dataImportLog.setLogType(rs.getString("logType"));
            return dataImportLog;
        }
    }



}
