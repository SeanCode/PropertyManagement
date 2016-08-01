package cn.edu.cqupt.wyglzx.dao;

import cn.edu.cqupt.wyglzx.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Repository
public interface ArticleDao extends JpaRepository<ArticleEntity, Long> {

    @Query(value = "select * from article where type = :type and  weight >= 0 order by create_time DESC limit :offset,10", nativeQuery = true)
    List<ArticleEntity> getArticleListByTypeAndOffset(@Param("type") Integer type, @Param("offset") Integer offset);

    @Query(value = "select * from article where weight >= 0 order by create_time DESC limit 10", nativeQuery = true)
    List<ArticleEntity> getLatestArticles();

}
