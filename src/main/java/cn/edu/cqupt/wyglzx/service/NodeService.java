package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.NodeDao;
import cn.edu.cqupt.wyglzx.entity.NodeEntity;
import cn.edu.cqupt.wyglzx.entity.NodeOwnerEntity;
import cn.edu.cqupt.wyglzx.exception.InvalidParamsException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        if (area >= 0) {
            nodeEntity.setArea(area);
        }
        if (price >= 0) {
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

    public NodeEntity addNode(String name, Long parentId, String code, String path, Integer type, Double area, Double price, Double fee, String ownership, String remark) {
        checkNodeById(parentId);
        if (type <= 0 || type > 3) {
            throw new InvalidParamsException("type");
        }
        NodeEntity node = new NodeEntity();
        node.setName(name);
        node.setParentId(NodeEntity.generateParentIdString(parentId));
        node.setType(type);
        if (StringUtils.isNotBlank(code)) {
            node.setCode(code);
        }
        if (StringUtils.isNotBlank(path)) {
            node.setPath(path);
        }
        if (StringUtils.isNotBlank(ownership)) {
            node.setOwnership(ownership);
        }
        if (StringUtils.isNotBlank(remark)) {
            node.setRemark(remark);
        }
        if (area >= 0) {
            node.setArea(area);
        }
        if (price >= 0) {
            node.setPrice(price);
        }
        if (fee >= 0) {
            node.setFee(fee);
        }
        node.setCreateTime(Util.time());
        node.setUpdateTime(node.getCreateTime());

        node = nodeDao.save(node);
        return node;
    }

    public NodeEntity copyNode(Long parentId, Long id) {
        checkNodeById(parentId);

        NodeEntity node = checkNodeById(id);

        node.setParentId(Util.stickIdToIdString(node.getParentId(), parentId));

        node = nodeDao.save(node);
        return node;
    }

    public void removeNode(Long parentId, Long id) {
        NodeEntity node = checkNodeById(id);
        List<String> parentIdList = Util.explodeIdString(node.getParentId());
        if (!parentIdList.contains(parentId + "")) {
            throw new NotExistsException();
        }
        List<String> newIdList = new ArrayList<>();
        for (String pId : parentIdList) {
            if (!pId.equals(parentId + "")) {
                newIdList.add(pId);
            }
        }
        node.setParentId(Util.implodeIdString(newIdList));

        nodeDao.save(node);
    }

    public void deleteNode(Long id) {
        NodeEntity node = checkNodeById(id);
        node.setWeight(-1);
        nodeDao.save(node);
    }
}
