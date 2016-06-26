package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface DepartmentDao extends CrudRepository<DepartmentEntity, Long> {


    @Query(name = "select * from department where id = :id and weight >= 0", nativeQuery = true)
    DepartmentEntity getDepartmentById(@Param("id") Long id);

    @Query(name = "select * from department where name = :name and weight >= 0", nativeQuery = true)
    DepartmentEntity getDepartmentByName(@Param("name") String name);

    @Query(name = "select * from department where parent_id = :parent_id and name = :name and weight >= 0", nativeQuery = true)
    DepartmentEntity getDepartmentByParentIdAndName(@Param("parent_id") Long parentId, @Param("name") String name);

    @Query(name = "select * from department where root_id = :root_id and weight >= 0", nativeQuery = true)
    List<DepartmentEntity> getDepartmentListByRootId(@Param("root_id") Long rootId);

}
