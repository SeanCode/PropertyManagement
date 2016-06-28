package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.NodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface NodeDao extends JpaRepository<NodeEntity, Long> {

    @Query(value = "select * from node where id = :id and weight >= 0", nativeQuery = true)
    NodeEntity getNodeById(@Param("id") Long id);

    @Query(value = "select * from node where parent_id = :parent_id and weight >= 0", nativeQuery = true)
    List<NodeEntity> getNodeListByParentId(@Param("parent_id") Long parentId);

    @Query(value = "select * from node where parent_id = :parent_id and type != 3 and weight >= 0", nativeQuery = true)
    List<NodeEntity> getNodeRoomListByParentId(@Param("parent_id") Long parentId);

    @Query(value = "select * from node where parent_id = :parent_id and type != 2 and weight >= 0", nativeQuery = true)
    List<NodeEntity> getNodeInstitutionListByParentId(@Param("parent_id") Long parentId);

}
