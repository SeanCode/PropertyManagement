package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.NodeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface NodeDao extends CrudRepository<NodeEntity, Long> {

    @Query(name = "select * from node where id = :id and weight >= 0", nativeQuery = true)
    NodeEntity getNodeById(@Param("id") Long id);

    @Query(name = "select * from node where parent_id = :parent_id and weight >= 0", nativeQuery = true)
    List<NodeEntity> getNodeListByParentId(@Param("parent_id") Long parentId);

}
