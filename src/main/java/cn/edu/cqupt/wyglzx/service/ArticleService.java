package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.common.Util;
import cn.edu.cqupt.wyglzx.dao.ArticleDao;
import cn.edu.cqupt.wyglzx.dao.AttachmentDao;
import cn.edu.cqupt.wyglzx.entity.ArticleEntity;
import cn.edu.cqupt.wyglzx.exception.NotAllowedException;
import cn.edu.cqupt.wyglzx.exception.NotExistsException;
import cn.edu.cqupt.wyglzx.exception.ParamNotSetException;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    AuthenticationFacadeService authenticationFacadeService;

    public List<ArticleEntity> getLatest(Integer page) {
        return articleDao.getAllTypeArticles((page - 1) * 10);
    }

    public List<ArticleEntity> getArticleListByType(Integer type, Integer page) {
        if (page <= 1) {
            page = 1;
        }
        switch (type) {
            case 0:
                return articleDao.getAllTypeArticles((page - 1) * 10);
            case 10000:
                return articleDao.getGuideListByOffset((page - 1) * 10);
            case 20000:
                return articleDao.getNewsListByOffset((page - 1) * 10);
            case 30000:
                return articleDao.getLogListByOffset((page - 1) * 10);
            default:
                return articleDao.getArticleListByTypeAndOffset(type, (page - 1) * 10);
        }
    }

    public Integer getListAmountByType(Integer type) {
        if (type == 0) {
            return articleDao.getListAmountByType();
        }
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
        return articleDao.save(articleEntity);
    }

    public ArticleEntity getArticlePrevious(Long createTime, Integer type) {

        return articleDao.getArticlePrevious(createTime, type);
    }

    public ArticleEntity getArticleNext(Long createTime, Integer type) {

        return articleDao.getArticleNext(createTime, type);
    }

    @Transactional
    public ArticleEntity saveArticle(String title, Integer type, String content, String imgs, String attachments) {

        if (authenticationFacadeService.getAuthentication().hasAuthorizedArticle()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        if (type == 0) {
            throw new ParamNotSetException("type");
        }
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setCreateTime(Util.time());
        articleEntity.setType(type);
        articleEntity.setAdminId(authenticationFacadeService.getAuthentication().getId());
        articleEntity.setUpdateTime(Util.time());
        if (StringUtils.isNotBlank(title)) {
            articleEntity.setTitle(title);
        }
        if (StringUtils.isNotBlank(content)) {
            articleEntity.setContent(content);
        }
        if (StringUtils.isNotBlank(imgs)) {
            articleEntity.setImg(imgs);
        }
        if (StringUtils.isNotBlank(attachments)) {
            articleEntity.setAttachments(Util.filter(attachments, ","));
        }

        return articleDao.save(articleEntity);
    }

    public ArticleEntity updateArticle(Long id, String title, String content, String attachments, String imgs) {

        if (authenticationFacadeService.getAuthentication().hasAuthorizedArticle()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        ArticleEntity articleEntity = articleDao.getArticleById(id);
        if (articleEntity == null) {
            throw new NotExistsException();
        }
        if (StringUtils.isNotBlank(title)) {
            articleEntity.setTitle(title);
        }
        if (StringUtils.isNotBlank(content)) {
            articleEntity.setContent(content);
        }
        if (StringUtils.isNotBlank(attachments)) {
            articleEntity.setAttachments(attachments);
        }
        if (StringUtils.isNotBlank(imgs)) {
            articleEntity.setImg(imgs);
        }
        articleEntity.setUpdateTime(Util.time());
        return articleDao.save(articleEntity);
    }

    public void deleteArticle(Long id) {

        if (!authenticationFacadeService.getAuthentication().hasAuthorizedArticle()) {
            throw new NotAllowedException("尚未授权!请联系管理员!");
        }
        ArticleEntity articleEntity = articleDao.getArticleById(id);
        if (articleEntity == null) {
            throw new NotExistsException();
        }
        articleEntity.setWeight(-1);
        articleDao.save(articleEntity);
    }
}
