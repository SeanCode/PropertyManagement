package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Config;
import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.MeterDao;
import cn.edu.cqupt.wyglzx.dao.RecordDao;
import cn.edu.cqupt.wyglzx.entity.MeterEntity;
import cn.edu.cqupt.wyglzx.entity.RecordEntity;
import cn.edu.cqupt.wyglzx.exception.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by cc on 16/6/25.
 */
@Component
@Transactional
public class RecordService {

    @Autowired
    RecordDao recordDao;

    @Autowired
    MeterDao meterDao;

    @Autowired
    MeterService meterService;

    @Autowired
    AuthenticationFacadeService authService;

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(RecordService.class);

    public void checkNotExistsByMeterIdAndTime(Long meterId, Long time) {
        RecordEntity recordEntity = recordDao.getByMeterIdAndTime(meterId, time);
        if (recordEntity != null) {
            throw new ExistsException("当前时间数据已被录入, 请留意抄表时间");
        }
    }

    // 录入前的风险控制, 与去年同月的用量做比较,误差超过一定范围, 严格模式下则抛异常警告
    public int preInput(Long meterId, Long time, Double value, boolean strict) {
        int tag = RecordEntity.TAG_INIT;
        // 1.对比去年同月的用量, 目前阈值是给定的, 以后做成动态的
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTime(new Date(time * 1000));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        RecordEntity lastYear = recordDao.getMeterYearMonthRecord(meterId, year - 1, month);
        if (lastYear != null) {
            // 2倍则警告
            if (value >= 2 * lastYear.getEnd()) {
                tag = RecordEntity.TAG_WARNING;
                if (strict) {
                    throw new MeterInputException("系统录入警告, 请再次检查确认无误后提交");
                }
            }
        }
        // 2.上一个月的用量对比
        RecordEntity lastMonth = recordDao.getMeterYearMonthRecord(meterId, year, month - 1);
        if (lastMonth != null && tag == RecordEntity.TAG_INIT) {
            if (value - lastMonth.getEnd() < 0) {
                tag = RecordEntity.TAG_ERROR;
                if (strict) {
                    throw new MeterInputException("系统录入错误, 请再次检查确认无误后提交");
                }
            } else if (value >= 2 * lastMonth.getEnd()) {
                // 超过2倍
                tag = RecordEntity.TAG_WARNING;
                if (strict) {
                    throw new MeterInputException("系统录入警告, 请再次检查确认无误后提交");
                }
            }
        }
        // 3.直接比较阈值
        if (tag == RecordEntity.TAG_INIT) {
            if (value >= Config.RECORD_WARNING_VALUE) {
                tag = RecordEntity.TAG_WARNING;
                if (strict) {
                    throw new MeterInputException("系统录入警告, 请再次检查确认无误后提交");
                }
            } else {
                tag = RecordEntity.TAG_NORMAL;
            }
        }
        return tag;
    }

    // 经过风险控制过后的真正的录入
    public void input(MeterEntity meterEntity, Long time, Double value, String reader, String remark, int tag) {

        RecordEntity recordEntity = new RecordEntity();
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        calendar.setTime(new Date(time * 1000));
        recordEntity.setMeterId(meterEntity.getId());
        recordEntity.setMeterType(meterEntity.getType());
        recordEntity.setNodeId(meterEntity.getNodeId());
        recordEntity.setType(RecordEntity.TYPE_TEMP);
        recordEntity.setBegin(meterEntity.getCurrent());
        recordEntity.setOperatorId(authService.getAuthentication().getId());
        recordEntity.setYear(calendar.get(Calendar.YEAR));
        recordEntity.setMonth(calendar.get(Calendar.MONTH) + 1);
        recordEntity.setEnd(value);
        recordEntity.setTime(time);
        recordEntity.setTag(tag);
        if (StringUtils.isNotBlank(reader)) {
            recordEntity.setReader(reader);
        } else {
            recordEntity.setReader(authService.getAuthentication().getAdmin().getUsername());
        }
        if (StringUtils.isNotBlank(remark)) {
            recordEntity.setRemark(remark);
        }
        recordEntity.setCreateTime(Util.time());
        recordEntity.setUpdateTime(recordEntity.getCreateTime());

        recordDao.save(recordEntity);

        // 更新表的上次录入时间和状态, 审核通过后,再更新一次录入状态和当前值
        meterEntity.setLastInputTime(Util.time());
        meterEntity.setLastInputStatus(MeterEntity.INPUT_STATUS_PENDING);
        meterDao.save(meterEntity);
    }

    // 录入入口
    public List<MeterEntity> inputMeter(Long meterId, Long time, Double value, String reader, String remark, boolean force) {
        // 数据校验
        if (value == null || value <= 0) {
            throw new InvalidParamsException("value");
        }
        checkNotExistsByMeterIdAndTime(meterId, time);
        // 风险控制 强制提交则不走严格模式
        int tag = preInput(meterId, time, value, !force);

        MeterEntity meterEntity = meterService.checkMeterById(meterId);

        // 录入
        input(meterEntity, time, value, reader, remark, tag);

        return meterService.getMeterList(meterEntity.getNodeId());
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

    public List<RecordEntity> getPendingList(Integer page) {
        if (page < 1) {
            page = 1;
        }
        return recordDao.getPendingList((page - 1) * 10);
    }

    /**
     * 返回审核历史
     *
     * @param page 页数 从0开始
     * @param all  返回所有人审核的还是仅自己审核的
     * @return 列表
     */
    public List<RecordEntity> getCheckedList(Integer page, Boolean all) {
        if (all) {
            return recordDao.getAllCheckedList(page * 10);
        }
        return recordDao.getCheckedListByAdmin(authService.getAuthentication().getId(), page * 10);
    }

    /**
     * 1. 使用id字符串 更新临时录入record 数据status
     * 2. 从record中拿到meter_id 去更新meter表的last_input_status
     * 3. 生成或更新存档record
     */
    @Transactional
    public void checkRecord(String id, Integer status) {
        if (status != RecordEntity.STATUS_REJECTED && status != RecordEntity.STATUS_APPROVED) {
            throw new InvalidParamsException("status");
        }
        List<String> ids = Util.explodeIdString(id);
        List<Long> idList = ids.stream().map((Function<String, Long>) Long::parseLong).collect(Collectors.toList());
        List<RecordEntity> list = recordDao.findByIdIn(idList);
        if (list.size() <= 0) {
            return;
        }

        for (RecordEntity record : list) {
            if (record.getWeight() < 0 || record.getType() != RecordEntity.TYPE_TEMP || record.getStatus() != RecordEntity.STATUS_PENDING) {
                continue;
            }
            // 先更新临时记录
            Long adminId = authService.getAuthentication().getId();
            record.setReviewerId(adminId);
            record.setStatus(status);
            record.setUpdateTime(Util.time());
            // 再更新或生成存档记录,用于统计
            archiveRecord(record);
            // 更新 表的last_input_status和current
            updateMeterLastInputStatus(record);
            // save 临时记录
            recordDao.save(record);
        }
    }

    private void archiveRecord(RecordEntity record) {

        RecordEntity archived = recordDao.getArchivedRecord(record.getMeterId(), record.getYear(), record.getMonth());
        boolean flag = false;
        if (archived == null) {
            archived = new RecordEntity();
            archived.setType(RecordEntity.TYPE_ARCHIVE);
            archived.setOperatorId(record.getReviewerId()); // 记录操作人的id
            archived.setNodeId(record.getNodeId());
            archived.setMeterId(record.getMeterId());
            archived.setMeterType(record.getMeterType());
            archived.setCreateTime(Util.time());
            archived.setYear(record.getYear());
            archived.setMonth(record.getMonth());
            archived.setBegin(record.getBegin());
            archived.setTime(Util.time());
            flag = true;
        }
        archived.setEnd(record.getEnd());
        archived.setReviewerId(record.getReviewerId()); // 记录审核人的id
        archived.setUpdateTime(flag ? archived.getCreateTime() : Util.time());

        recordDao.save(archived);
    }

    private void updateMeterLastInputStatus(RecordEntity record) {
        MeterEntity meter = meterDao.getMeterById(record.getMeterId());
        if (meter.getStatus() == MeterEntity.STATUS_VALID && meter.getWeight() >= 0) {
            meter.setUpdateTime(Util.time());
//          如果通过审核, 则更新当前度数
            if (record.getStatus() == RecordEntity.STATUS_APPROVED) {
                meter.setCurrent(record.getEnd());
                meter.setLastInputStatus(MeterEntity.INPUT_STATUS_ACCEPT);
            } else if (record.getStatus() == RecordEntity.STATUS_REJECTED) {
                meter.setLastInputStatus(MeterEntity.INPUT_STATUS_REJECTED);
            }
            meterDao.save(meter);
        }
    }

    public Integer getPendingCount() {

        return recordDao.getPendingCount();
    }
}
