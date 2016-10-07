package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.dao.NodeOwnerDao;
import cn.edu.cqupt.wyglzx.dao.RecordDao;
import cn.edu.cqupt.wyglzx.entity.NodeEntity;
import cn.edu.cqupt.wyglzx.entity.RecordEntity;
import cn.edu.cqupt.wyglzx.model.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by cc on 16/10/7.
 */
@Component
public class StatService {

    @Autowired
    RecordDao recordDao;

    @Autowired
    NodeOwnerDao nodeOwnerDao;

    @Autowired
    NodeService nodeService;

    @Autowired
    NodeOwnerService nodeOwnerService;

    public List<Statistics> getStatByOwner(Long ownerId, int ownerType, int type) {

        NodeEntity node = nodeOwnerService.getNodeByOwner(ownerId, ownerType).getNode();

        return getStatByNodeIdAndType(node.getId(), ownerId, type);
    }

    public List<Statistics> getStatByNode(Long nodeId, int type) {
        NodeEntity node = nodeService.getNodeDetail(nodeId);

        return getStatByNodeIdAndType(node.getId(), 0, type);
    }

    public List<Statistics> getStatByNodeIdAndType(Long nodeId, long ownerId, int type) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        List<Statistics> list = new ArrayList<>();
        list.add(getYearStat(nodeId, ownerId, type, calendar.get(Calendar.YEAR)));
        list.add(getYearStat(nodeId, ownerId, type, calendar.get(Calendar.YEAR) - 1));

        return list;
    }

    public Statistics getYearStat(Long nodeId, Long ownerId, int type, int year) {

        List<RecordEntity> recordList = recordDao.getNodeArchiveListByYearAngType(nodeId, year, type);

        Statistics stat = new Statistics();
        stat.setNodeId(nodeId);
        stat.setOwnerId(ownerId);
        stat.setType(type);
        stat.setYear(year);
        List<Double> data = new LinkedList<>();// 存放最终十二个月的数据
        Map<Integer, Double> recordMap = new HashMap<>();

        for (RecordEntity record : recordList) {
            int month = record.getMonth();
            Double value = recordMap.get(month);
            if (value == null) {
                recordMap.put(month, record.getEnd() - record.getBegin());
            } else {
                recordMap.put(month, value + (record.getEnd() - record.getBegin()));
            }
        }
        for (int i = 1; i <= 12; i++) {
            Double r = recordMap.get(i);
            data.add(r == null ? 0 : r);
        }
        stat.setData(data);

        return stat;
    }

}
