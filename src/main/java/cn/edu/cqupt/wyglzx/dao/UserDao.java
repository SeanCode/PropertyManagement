package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    @Query(value = "select * from user where department_id = :department_id and weight >= 0", nativeQuery = true)
    List<UserEntity> getUserListByDepartmentId(@Param("department_id") Long departmentId);

    @Query(value = "select * from user where id = :id and weight >= 0", nativeQuery = true)
    UserEntity getUserById(@Param("id") Long id);

    @Query(value = "select * from user where id_card = :id_card and weight >= 0", nativeQuery = true)
    UserEntity getUserByIdCard(@Param("id_card") String idCard);

    @Query(value = "select count(*) from user where weight >= 0", nativeQuery = true)
    Integer getUserAmount();

    @Query(value = "select * from user where name like '%:n%' and weight >= 0", nativeQuery = true)
    List<UserEntity> getUserListByNameLike(@Param("n") String name);

}
