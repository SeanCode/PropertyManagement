package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.dao.NodeDao;
import cn.edu.cqupt.wyglzx.entity.NodeEntity;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
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
}
