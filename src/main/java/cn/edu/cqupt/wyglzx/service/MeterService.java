package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.MeterDao;
import cn.edu.cqupt.wyglzx.entity.MeterEntity;
import cn.edu.cqupt.wyglzx.exception.ExistsException;
import cn.edu.cqupt.wyglzx.exception.InvalidParamsException;
import cn.edu.cqupt.wyglzx.exception.NotAllowedException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cc on 16/6/25.
 */
@Component
public class MeterService {

    @Autowired
    MeterDao meterDao;

    public MeterEntity checkMeterById(Long id) {
        MeterEntity meterEntity = meterDao.getMeterById(id);
        if (meterEntity == null) {
            throw new NotExistsException("表不存在");
        }
        return meterEntity;
    }

    public List<MeterEntity> getNodeNormalMeterList(Long nodeId) {

        List<MeterEntity> list = meterDao.getMeterNormalListByNodeId(nodeId);

        for (MeterEntity meter : list) {
            meter.setParent(meterDao.getMeterById(meter.getParentId()));
        }

        return list;
    }

    public List<MeterEntity> getNodeCheckMeterList(Long nodeId) {

        List<MeterEntity> list = meterDao.getMeterCheckListByNodeId(nodeId);

        for (MeterEntity meter : list) {
            meter.setParent(meterDao.getMeterById(meter.getParentId()));
        }

        return list;
    }

    public List<MeterEntity> getNodeChildrenMeter(Long nodeId) {

        List<MeterEntity> list = meterDao.getMeterChildrenByNodeId(nodeId);

        for (MeterEntity meter : list) {
            meter.setParent(meterDao.getMeterById(meter.getParentId()));
        }

        return list;
    }

    public MeterEntity addNormalMeter(String name, Long nodeId, String code, Integer type, Integer rate, Double begin, String nameplate, String manufacturers, String purchaser, Double cost, Long buyTime, Long productTime, String remark) {

        if (StringUtils.isBlank(name)) {
            throw new InvalidParamsException("name");
        }
        if (nodeId <= 0) {
            throw new InvalidParamsException("node_id");
        }
        if (type <= 0 || type > MeterEntity.TYPE_GAS) {
            throw new InvalidParamsException("type");
        }
        MeterEntity meter = new MeterEntity();
        meter.setNodeId(nodeId);
        meter.setName(name);
        meter.setType(type);
        if (rate > 0) {
            meter.setRate(rate);
        }
        if (begin != 0) {
            meter.setBegin(begin);
            meter.setCurrent(begin);
        }
        if (cost > 0) {
            meter.setCost(cost);
        }
        if (buyTime > 0) {
            meter.setBuyTime(buyTime);
        }
        if (productTime > 0) {
            meter.setProductTime(productTime);
        }
        if (StringUtils.isNotBlank(code)) {
            meter.setCode(code);
        }
        if (StringUtils.isNotBlank(nameplate)) {
            meter.setNameplate(nameplate);
        }
        if (StringUtils.isNotBlank(remark)) {
            meter.setRemark(remark);
        }
        if (StringUtils.isNotBlank(manufacturers)) {
            meter.setManufacturers(manufacturers);
        }
        if (StringUtils.isNotBlank(purchaser)) {
            meter.setPurchaser(purchaser);
        }
        meter.setCreateTime(Util.time());
        meter.setUpdateTime(meter.getCreateTime());

        meter = meterDao.save(meter);

        return meter;
    }

    public MeterEntity updateMeter(Long id, String name, String code, String remark) {
        MeterEntity meter = checkMeterById(id);

        if (StringUtils.isNotBlank(name)) {
            meter.setName(name);
        }
        if (StringUtils.isNotBlank(code)) {
            meter.setCode(code);
        }
        if (StringUtils.isNotBlank(remark)) {
            meter.setRemark(remark);
        }
        meter.setUpdateTime(Util.time());
        meter = meterDao.save(meter);

        return meter;
    }

    public void removeMeter(Long id, Long nodeId) {
        MeterEntity meter = meterDao.getMeterByIdAndNodeId(id, nodeId);
        if (meter == null) {
            throw new NotExistsException();
        }
        if (meter.getType() < 4) {// 主表
            // 是否有检查表
            List<MeterEntity> checkList = meterDao.getMeterCheckListByNodeId(meter.getNodeId());
            if (checkList.size() > 0) {
                throw new ExistsException("存在检查表");
            }
            List<MeterEntity> children = meterDao.getMeterChildrenByNodeId(meter.getNodeId());
            if (children.size() > 0) {
                throw new ExistsException("存在分表");
            }
        }
        meter.setWeight(-1);
        meter.setUpdateTime(Util.time());
        meterDao.save(meter);
    }

    public void setAsChild(Long id, Long nodeId, Long parentId) {
        MeterEntity meter = checkMeterById(id);
        if (nodeId != meter.getNodeId()) {
            throw new NotExistsException();
        }
        MeterEntity parent = checkMeterById(parentId);

        meter.setParentId(parent.getId());
        meter.setUpdateTime(Util.time());

        meterDao.save(meter);
    }

    @Transactional
    public void setAsCheck(Long id, Long nodeId) {
        MeterEntity meter = meterDao.getMeterByIdAndNodeId(id, nodeId);
        if (meter == null) {
            throw new NotExistsException();
        }
        MeterEntity parent = meterDao.getFirstNormalMeterByNodeIdAndType(nodeId, meter.getType());
        if (parent == null) {
            throw new NotExistsException();
        }
        parent.setCheckMeterId(meter.getId());

        meter.setParentId(parent.getId());

        if (meter.getType() < 4) {
            meter.setType(meter.getType() + 3);
        } else {
            throw new ExistsException();
        }

        meterDao.save(parent);
        meterDao.save(meter);
    }

    public MeterEntity getMeterDetail(Long id) {
        MeterEntity meterEntity = checkMeterById(id);
        meterEntity.setParent(meterDao.getMeterById(meterEntity.getParentId()));
        return meterEntity;
    }

    @Transactional
    public MeterEntity addCheckMeter(String name, String code, Integer type, Long parentId, Integer rate, Double begin, String nameplate, String manufacturers, String purchaser, Double cost, Long buyTime, Long productTime, String remark) {
        MeterEntity parent = checkMeterById(parentId);

        if (StringUtils.isBlank(name)) {
            throw new InvalidParamsException("name");
        }
        if (type <= MeterEntity.TYPE_GAS || type > MeterEntity.TYPE_CHECK_GAS) {
            throw new InvalidParamsException("type");
        }
        MeterEntity meter = new MeterEntity();
        meter.setNodeId(parent.getNodeId());
        meter.setParentId(parent.getId());
        meter.setName(name);
        meter.setType(type);
        if (rate > 0) {
            meter.setRate(rate);
        }
        if (begin != 0) {
            meter.setBegin(begin);
            meter.setCurrent(begin);
        }
        if (cost > 0) {
            meter.setCost(cost);
        }
        if (buyTime > 0) {
            meter.setBuyTime(buyTime);
        }
        if (productTime > 0) {
            meter.setProductTime(productTime);
        }
        if (StringUtils.isNotBlank(code)) {
            meter.setCode(code);
        }
        if (StringUtils.isNotBlank(nameplate)) {
            meter.setNameplate(nameplate);
        }
        if (StringUtils.isNotBlank(remark)) {
            meter.setRemark(remark);
        }
        if (StringUtils.isNotBlank(manufacturers)) {
            meter.setManufacturers(manufacturers);
        }
        if (StringUtils.isNotBlank(purchaser)) {
            meter.setPurchaser(purchaser);
        }
        meter.setCreateTime(Util.time());
        meter.setUpdateTime(meter.getCreateTime());

        meter = meterDao.save(meter);

        parent.setCheckMeterId(meter.getId());
        meterDao.save(parent);

        return meter;
    }

    @Transactional
    public void replaceMeter(Long id, Double end, String name, String code, Integer rate, Double begin, String nameplate, String manufacturers, String purchaser, Double cost, Long buyTime, Long productTime, String remark) {
        MeterEntity oldMeter = checkMeterById(id);
        if (oldMeter.getCurrent() != end) {
            throw new NotAllowedException("旧表止度尚未成功录入, 请先录入");
        }
        if (oldMeter.getLastInputStatus() == MeterEntity.INPUT_STATUS_PENDING) {
            throw new NotAllowedException("旧表录入数据正在审核中, 请先等待通过审核");
        }
        MeterEntity meterEntity = new MeterEntity();
        meterEntity.setParentId(oldMeter.getParentId());
        meterEntity.setType(oldMeter.getType());
        meterEntity.setName(oldMeter.getName());
        meterEntity.setNodeId(oldMeter.getNodeId());
        meterEntity.setCode((oldMeter.getCode()));
        if (StringUtils.isNotBlank(name)) {
            meterEntity.setName(name);
        }
        if (StringUtils.isNotBlank(code)) {
            meterEntity.setCode(code);
        }
        if (rate != 0) {
            meterEntity.setRate(rate);
        }
        if (begin != 0) {
            meterEntity.setBegin(begin);
            meterEntity.setCurrent(begin);
        }
        if (StringUtils.isNotBlank(nameplate)) {
            meterEntity.setNameplate(nameplate);
        }
        if (StringUtils.isNotBlank(manufacturers)) {
            meterEntity.setManufacturers(manufacturers);
        }
        if (StringUtils.isNotBlank(purchaser)) {
            meterEntity.setPurchaser(purchaser);
        }
        if (StringUtils.isNotBlank(remark)) {
            meterEntity.setRemark(remark);
        }
        if (cost > 0) {
            meterEntity.setCost(cost);
        }
        if (buyTime > 0) {
            meterEntity.setBuyTime(buyTime);
        }
        if (productTime > 0) {
            meterEntity.setProductTime(productTime);
        }
        meterEntity.setCreateTime(Util.time());
        meterEntity.setUpdateTime(meterEntity.getCreateTime());

        meterDao.save(meterEntity);

        oldMeter.setStatus(MeterEntity.STATUS_INVALID);
        oldMeter.setUpdateTime(Util.time());
        meterDao.save(oldMeter);
    }

    public List<MeterEntity> getMeterList(Long nodeId) {

        List<MeterEntity> list = meterDao.getMeterListByNodeId(nodeId);

        for (MeterEntity meter : list) {
            meter.setParent(meterDao.getMeterById(meter.getParentId()));
        }

        return list;
    }
}
