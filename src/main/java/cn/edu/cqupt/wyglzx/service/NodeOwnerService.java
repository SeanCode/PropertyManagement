package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.InstitutionDao;
import cn.edu.cqupt.wyglzx.dao.NodeDao;
import cn.edu.cqupt.wyglzx.dao.NodeOwnerDao;
import cn.edu.cqupt.wyglzx.dao.UserDao;
import cn.edu.cqupt.wyglzx.entity.NodeOwnerEntity;
import cn.edu.cqupt.wyglzx.exception.ExistsException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 16/6/25.
 */
@Component
public class NodeOwnerService {

    @Autowired
    NodeOwnerDao nodeOwnerDao;

    @Autowired
    NodeDao nodeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    InstitutionDao institutionDao;

    public NodeOwnerEntity checkByNodeAndOwner(Long nodeId, Long ownerId, Integer ownerType) {
        NodeOwnerEntity nodeOwnerEntity = nodeOwnerDao.getNodeOwner(nodeId, ownerId, ownerType);
        if (nodeOwnerEntity == null) {
            throw new NotExistsException();
        }
        return nodeOwnerEntity;
    }

    public NodeOwnerEntity checkByOwner(Long ownerId, Integer ownerType) {
        NodeOwnerEntity nodeOwnerEntity = nodeOwnerDao.getNodeOwner(ownerId, ownerType);
        if (nodeOwnerEntity == null) {
            throw new NotExistsException();
        }
        return nodeOwnerEntity;
    }

    public NodeOwnerEntity checkByNode(Long nodeId) {
        NodeOwnerEntity nodeOwnerEntity = nodeOwnerDao.getNodeOwner(nodeId);
        if (nodeOwnerEntity == null) {
            throw new NotExistsException();
        }
        return nodeOwnerEntity;
    }

    public NodeOwnerEntity addNodeOwner(Long nodeId, Long ownerId, Integer ownerType) {
        NodeOwnerEntity nodeOwnerEntity = nodeOwnerDao.getValidNodeOwner(nodeId, ownerId, ownerType);
        if (nodeOwnerEntity != null) {
            throw new ExistsException();
        }
        nodeOwnerEntity = new NodeOwnerEntity();
        nodeOwnerEntity.setNodeId(nodeId);
        nodeOwnerEntity.setOwnerId(ownerId);
        nodeOwnerEntity.setOwnerType(ownerType);
        nodeOwnerEntity.setStatus(NodeOwnerEntity.STATUS_VALID);
        nodeOwnerEntity.setCreateTime(Util.time());
        nodeOwnerEntity.setUpdateTime(nodeOwnerEntity.getCreateTime());

        nodeOwnerEntity = nodeOwnerDao.save(nodeOwnerEntity);
        return nodeOwnerEntity;
    }

    public NodeOwnerEntity invalidNodeOwner(Long nodeId, Long userId, Integer ownerType) {
        NodeOwnerEntity nodeOwnerEntity = checkByNodeAndOwner(nodeId, userId, ownerType);
        nodeOwnerEntity.setStatus(NodeOwnerEntity.STATUS_INVALID);
        nodeOwnerEntity.setWeight(-1);
        nodeOwnerEntity.setUpdateTime(Util.time());

        nodeOwnerEntity = nodeOwnerDao.save(nodeOwnerEntity);

        return nodeOwnerEntity;
    }

    public NodeOwnerEntity getNodeByOwner(Long ownerId, Integer ownerType) {
        NodeOwnerEntity nodeOwnerEntity = checkByOwner(ownerId, ownerType);
        nodeOwnerEntity.setNode(nodeDao.getNodeById(nodeOwnerEntity.getNodeId()));

        return nodeOwnerEntity;
    }

    public NodeOwnerEntity getOwnerByNode(Long nodeId) {
        NodeOwnerEntity nodeOwnerEntity = nodeOwnerDao.getNodeOwnerByNode(nodeId);
        if (nodeOwnerEntity == null || nodeOwnerEntity.getStatus() == NodeOwnerEntity.STATUS_INVALID) {
            throw new NotExistsException();
        }
        switch (nodeOwnerEntity.getOwnerType()) {
            case NodeOwnerEntity.OWNER_TYPE_USER:
                nodeOwnerEntity.setUser(userDao.getUserById(nodeOwnerEntity.getOwnerId()));
                break;
            case NodeOwnerEntity.OWNER_TYPE_INSTITUTION:
                nodeOwnerEntity.setInstitution(institutionDao.getInstitutionById(nodeOwnerEntity.getOwnerId()));
                break;
            default:
                break;
        }

        return nodeOwnerEntity;
    }
}
