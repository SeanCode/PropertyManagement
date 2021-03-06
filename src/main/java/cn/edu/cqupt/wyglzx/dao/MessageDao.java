package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 16/6/24.
 */
@Repository
public interface MessageDao extends JpaRepository<MessageEntity, Long> {

}
