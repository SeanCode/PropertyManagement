package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.NodeDao;
import cn.edu.cqupt.wyglzx.entity.NodeEntity;
import cn.edu.cqupt.wyglzx.entity.NodeOwnerEntity;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cc on 16/6/25.
 */
@Component
public class NodeService {

    @Autowired
    NodeDao nodeDao;

    public NodeEntity checkNodeById(Long id) {
        NodeEntity nodeEntity = nodeDao.getNodeById(id);
        if (nodeEntity == null) {
            throw new NotExistsException();
        }
        return nodeEntity;
    }

    public List<NodeEntity> getNodeChildren(Long parentId, Integer type) {
        switch (type) {
            case NodeEntity.TYPE_ROOM:
                return nodeDao.getNodeRoomListByParentId(parentId);
            case NodeEntity.TYPE_INSTITUTION:
                return nodeDao.getNodeInstitutionListByParentId(parentId);
            default:
                return nodeDao.getNodeListByParentId(parentId);
        }
    }

    public NodeEntity getNodeTreeRoot() {

        NodeEntity root = checkNodeById(1L);
        root.setChildren(nodeDao.getNodeListByParentId(root.getId()));

        return root;
    }

    public NodeEntity getNodeDetail(Long id) {

        NodeEntity nodeEntity = checkNodeById(id);

        return nodeEntity;
    }

    public NodeEntity updateNodeInfo(Long id, String name, String code, String path, Integer type, Double area, Double price, Double fee, String ownership, String remark) {

        NodeEntity nodeEntity = checkNodeById(id);

        if (StringUtils.isNotBlank(name)) {
            nodeEntity.setName(name);
        }
        if (StringUtils.isNotBlank(code)) {
            nodeEntity.setCode(code);
        }
        if (StringUtils.isNotBlank(path)) {
            nodeEntity.setPath(path);
        }
        if (type > 0 || type < NodeEntity.TYPE_INSTITUTION) {
            nodeEntity.setType(type);
        }
        if (area > 0) {
            nodeEntity.setArea(area);
        }
        if (price > 0) {
            nodeEntity.setPrice(price);
        }
        if (fee >= 0) {
            nodeEntity.setFee(fee);
        }
        if (StringUtils.isNotBlank(ownership)) {
            nodeEntity.setOwnership(ownership);
        }
        if (StringUtils.isNotBlank(remark)) {
            nodeEntity.setRemark(remark);
        }
        nodeEntity.setUpdateTime(Util.time());

        nodeEntity = nodeDao.save(nodeEntity);

        return nodeEntity;
    }
}
