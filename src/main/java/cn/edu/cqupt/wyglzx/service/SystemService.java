package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.MeterDao;
import cn.edu.cqupt.wyglzx.dao.NodeDao;
import cn.edu.cqupt.wyglzx.entity.MeterEntity;
import cn.edu.cqupt.wyglzx.entity.NodeEntity;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by cc on 16/11/10.
 */
@Component
public class SystemService {

    @Autowired
    NodeDao nodeDao;

    @Autowired
    MeterDao meterDao;

    //    @Transactional
    public void addCodeForNodeAll() throws PinyinException {
        List<BigInteger> nodes = nodeDao.getAllNodesId();
        for (BigInteger nodeId : nodes) {
            if (nodeId.intValue() != 1) {
                NodeEntity node = nodeDao.getNodeById(Integer.toUnsignedLong(nodeId.intValue()));

                MeterEntity s = new MeterEntity();
                s.setNodeId(node.getId());
                s.setCode(node.getCode() + "/s/1");
                s.setName("水表1");
                s.setType(MeterEntity.TYPE_WATER);
                s.setCreateTime(Util.time());
                s.setUpdateTime(s.getCreateTime());
                meterDao.save(s);


                MeterEntity d = new MeterEntity();
                d.setNodeId(node.getId());
                d.setCode(node.getCode() + "/d/1");
                d.setName("电表1");
                d.setType(MeterEntity.TYPE_ELE);
                d.setCreateTime(Util.time());
                d.setUpdateTime(d.getCreateTime());
                meterDao.save(d);


                MeterEntity q = new MeterEntity();
                q.setNodeId(node.getId());
                q.setCode(node.getCode() + "/q/1");
                q.setName("气表1");
                q.setType(MeterEntity.TYPE_GAS);
                q.setCreateTime(Util.time());
                q.setUpdateTime(q.getCreateTime());
                meterDao.save(q);

//                String p = node.getParentId();
//                Long id = Long.valueOf(p.substring(1, p.length() - 1));
//
//                NodeEntity parent = nodeDao.getNodeById(id);
//                Integer level = parent.getLevel();
//                node.setLevel(level + 1);
//
//                if (StringUtils.isEmpty(node.getCode())) {
//                    String code = parent.getCode() + "/" + PinyinHelper.getShortPinyin(node.getName());
//                    node.setCode(code);
//                }
//                nodeDao.save(node);
            }

        }
    }

}
