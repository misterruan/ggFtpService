package com.ytx.service;

import com.ytx.dao.DataImportLogDao;
import com.ytx.model.DataImportLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rock on 2016/12/29.
 */
@Service
@Transactional
public class DataLogService {

    @Autowired
    private DataImportLogDao dataImportLogDao;

    public void createDataLog(DataImportLog dataImportLog) {
        dataImportLogDao.createDataLog(dataImportLog);
    }

    public void updateDataLogStatusAndExceptionMessage(String dataLogStatus,String id,String exceptionMessage) {
        dataImportLogDao.updateDataLogStatusAndExceptionMessage(dataLogStatus,id,exceptionMessage);
    }

    public DataImportLog getDataLogById(String id) {
        return dataImportLogDao.getDataLogById(id);
    }

    public List<DataImportLog> getFailureLogList() {
        return dataImportLogDao.getFailureLogList();
    }


}
