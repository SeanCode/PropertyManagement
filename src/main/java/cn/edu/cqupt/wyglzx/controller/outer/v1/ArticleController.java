package cn.edu.cqupt.wyglzx.controller.outer.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.entity.ArticleEntity;
import cn.edu.cqupt.wyglzx.service.ArticleService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cc on 16/7/30.
 */
@RestController("Public.ArticleController")
@RequestMapping("/api/public/v1/articles")
@Component
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getArticleList(@RequestParam(value = "page", required = false, defaultValue = "1")Integer page) {

        DataResponse response = new DataResponse();
        response.put("article_list", articleService.getLatest(page));
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{type}")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getArticleList(@PathVariable("type") Integer type, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        DataResponse response = new DataResponse();
        response.put("article_list", articleService.getArticleListByType(type, page));
        response.put("count", articleService.getListAmountByType(type));
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{type}/{id}")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getArticleContent(@PathVariable("type") Integer type, @PathVariable("id") Long id) {

        DataResponse response = new DataResponse();
        ArticleEntity article = articleService.getArticleContent(type, id);
        response.put("article_content", article);
        response.put("article_previous", articleService.getArticlePrevious(article.getCreateTime(), article.getType()));
        response.put("article_next", articleService.getArticleNext(article.getCreateTime(), article.getType()));
        return response;
    }

}
