package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.RecordDao;
import cn.edu.cqupt.wyglzx.entity.MeterEntity;
import cn.edu.cqupt.wyglzx.entity.RecordEntity;
import cn.edu.cqupt.wyglzx.exception.ExistsException;
import cn.edu.cqupt.wyglzx.exception.InvalidParamsException;
import cn.edu.cqupt.wyglzx.exception.NotAllowedException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by cc on 16/6/25.
 */
@Component
public class RecordService {

    @Autowired
    RecordDao recordDao;

    @Autowired
    MeterService meterService;

    @Autowired
    AuthenticationFacadeService authService;

    public void checkNotExistsByMeterIdAndTime(Long meterId, Long time) {
        RecordEntity recordEntity = recordDao.getByMeterIdAndTime(meterId, time);
        if (recordEntity != null) {
            throw new ExistsException("当前时间数据已被录入");
        }
    }

    public void input(Long meterId, Long time, Double value, String reader, String remark) {
        if (value == null || value < 0) {
            throw new InvalidParamsException("value");
        }
        checkNotExistsByMeterIdAndTime(meterId, time);
        MeterEntity meterEntity = meterService.checkMeterById(meterId);
        RecordEntity recordEntity = new RecordEntity();
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTime(new Date(time * 1000));
        recordEntity.setMeterId(meterEntity.getId());
        recordEntity.setMeterType(meterEntity.getType());
        recordEntity.setNodeId(meterEntity.getNodeId());
        recordEntity.setType(RecordEntity.TYPE_TEMP);
        recordEntity.setBegin(meterEntity.getBegin());
        recordEntity.setOperatorId(authService.getAuthentication().getId());
        recordEntity.setYear(calendar.get(Calendar.YEAR));
        recordEntity.setMonth(calendar.get(Calendar.MONTH) + 1);
        recordEntity.setEnd(value);
        recordEntity.setTime(time);
        if (StringUtils.isNotBlank(reader)) {
            recordEntity.setReader(reader);
        }
        if (StringUtils.isNotBlank(remark)) {
            recordEntity.setRemark(remark);
        }
        recordEntity.setCreateTime(Util.time());
        recordEntity.setUpdateTime(recordEntity.getCreateTime());

        recordDao.save(recordEntity);
    }

    public List<RecordEntity> getTempListByNodeAndTime(Long nodeId, Integer year, Integer month) {

        return recordDao.getRecordTempListByNodeAndYearMonth(nodeId, year, month);
    }

    public RecordEntity getLastYearMonthRecord(Long meterId) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int year = calendar.get(Calendar.YEAR) - 1;
        int month = calendar.get(Calendar.MONTH);

        return recordDao.getMeterYearMonthRecord(meterId, year, month);
    }

    public RecordEntity getLastMonthRecord(Long meterId) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) - 1;

        return recordDao.getMeterYearMonthRecord(meterId, year, month);
    }

    public void updateRecord(Long id, Double end, String reader, String remark) {
        RecordEntity recordEntity = recordDao.getRecordById(id);
        if (recordEntity == null) {
            throw new NotExistsException();
        }
        if (recordEntity.getStatus() != RecordEntity.STATUS_PENDING || recordEntity.getType() != RecordEntity.TYPE_TEMP) {
            throw new NotAllowedException();
        }
        if (end != null && end >= 0) {
            recordEntity.setEnd(end);
        }
        if (StringUtils.isNotBlank(reader)) {
            recordEntity.setReader(reader);
        }
        if (StringUtils.isNotBlank(remark)) {
            recordEntity.setRemark(remark);
        }
        recordDao.save(recordEntity);
    }
}
