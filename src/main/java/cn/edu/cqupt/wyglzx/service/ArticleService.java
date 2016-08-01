package cn.edu.cqupt.wyglzx.service;

import cn.edu.cqupt.wyglzx.dao.ArticleDao;
import cn.edu.cqupt.wyglzx.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cc on 16/7/31.
 */
@Component
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    public List<ArticleEntity> getLatest() {
        return articleDao.getLatestArticles();
    }

    //流程指南列表
    public List<ArticleEntity> getGuideProcessList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_GUIDE_PROCESS, page * 10);
    }

    public List<ArticleEntity> getGuideRuleList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_GUIDE_RULE, page * 10);
    }

    public List<ArticleEntity> getGuideWorkList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_GUIDE_WORK, page * 10);
    }

    public List<ArticleEntity> getGuideDownloadList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_GUIDE_DOWNLOAD, page * 10);
    }

    public List<ArticleEntity> getNewsHotList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_NEWS_HOT, page * 10);
    }

    public List<ArticleEntity> getNewsPostList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_NEWS_POST, page * 10);
    }

    public List<ArticleEntity> getLogDeviceList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_LOG_DEVICE, page * 10);
    }

    public List<ArticleEntity> getLogTeachingList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_LOG_TEACHING, page * 10);
    }

    public List<ArticleEntity> getLogDepartmentList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_LOG_DEPARTMENT, page * 10);
    }

    public List<ArticleEntity> getLogPropertyList(Integer page) {

        return articleDao.getArticleListByTypeAndOffset(ArticleEntity.TYPE_LOG_PROPERTY, page * 10);
    }

}
