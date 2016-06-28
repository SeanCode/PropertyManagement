package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.NodeOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 16/6/25.
 */
@Repository
public interface NodeOwnerDao extends JpaRepository<NodeOwnerEntity, Long> {

    @Query(value = "select * from node_owner where node_id = :node_id and owner_id = :owner_id and owner_type = :owner_type and weight >= 0 limit 0,1", nativeQuery = true)
    NodeOwnerEntity getNodeOwner(@Param("node_id") Long nodeId, @Param("owner_id") Long ownerId, @Param("owner_type") Integer ownerType);

    @Query(value = "select * from node_owner where node_id = :node_id and weight >= 0 limit 0,1", nativeQuery = true)
    NodeOwnerEntity getNodeOwner(@Param("node_id") Long nodeId);

    @Query(value = "select * from node_owner where owner_id = :owner_id and owner_type = :owner_type and weight >= 0 limit 0,1", nativeQuery = true)
    NodeOwnerEntity getNodeOwner(@Param("owner_id") Long ownerId, @Param("owner_type") Integer ownerType);

    @Query(value = "select * from node_owner where node_id = :node_id and weight >= 0 limit 0,1", nativeQuery = true)
    NodeOwnerEntity getNodeOwnerByNode(@Param("node_id") Long nodeId);
}
