package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.ArticleDao;
import cn.edu.cqupt.wyglzx.dao.AttachmentDao;
import cn.edu.cqupt.wyglzx.entity.ArticleEntity;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Component
@Transactional
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    AttachmentDao attachmentDao;

    public List<ArticleEntity> getLatest() {
        return articleDao.getLatestArticles();
    }

    public List<ArticleEntity> getArticleList(Integer type, Integer page) {
        if (page <= 1) {
            page = 1;
        }
        if (type == 20000) {
            return articleDao.getNewsListByOffset((page - 1) * 10);
        }
        return articleDao.getArticleListByTypeAndOffset(type, (page - 1) * 10);
    }

    public Integer getListAmountByType(Integer type) {
        return articleDao.getListAmountByType(type);
    }

    public ArticleEntity getArticleContent(Integer type, Long id) {

        ArticleEntity articleEntity = articleDao.getArticleByIdAndType(id, type);
        if (articleEntity == null) {
            throw new NotExistsException();
        }
        if (articleEntity.getAttachments().length() > 0) {
            articleEntity.setAttachmentList(attachmentDao.getAttachments(articleEntity.getAttachments()));
        }
        articleEntity.setRead(articleEntity.getRead() + 1);
        articleEntity.setUpdateTime(Util.time());
        return articleDao.save(articleEntity);
    }

    public ArticleEntity getArticlePrevious(Long createTime, Integer type) {

        return articleDao.getArticlePrevious(createTime, type);
    }

    public ArticleEntity getArticleNext(Long createTime, Integer type) {

        return articleDao.getArticleNext(createTime, type);
    }
}
