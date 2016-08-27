package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.ArticleService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cc on 16/8/27.
 */
@RestController("Private.ArticleController")
@RequestMapping("/api/private/v1/article")
@Component
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getArticleList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {

        DataResponse response = new DataResponse();
        response.put("article_list", articleService.getLatest(page));
        response.put("count", articleService.getListAmountByType(0));
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{type}")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getArticleList(@PathVariable(value = "type") Integer type, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page) {
        DataResponse response = new DataResponse();
        response.put("article_list", articleService.getArticleListByType(type, page));
        response.put("count", articleService.getListAmountByType(type));
        return response;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{type}/{id}")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getArticleContent(@PathVariable(value = "type") Integer type, @PathVariable(value = "id") Long id) {

        DataResponse response = new DataResponse();
        response.put("article", articleService.getArticleContent(type, id));
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse saveArticle(@RequestParam("type") Integer type,
                                    @RequestParam("title") String title,
                                    @RequestParam("content") String content,
                                    @RequestParam(value = "attachments", required = false, defaultValue = "") String attachments,
                                    @RequestParam(value = "imgs", required = false, defaultValue = "") String imgs) {

        DataResponse response = new DataResponse();
        response.put("article", articleService.saveArticle(title, type, content, imgs, attachments));
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateArticle(@RequestParam("id") Long id,
                                      @RequestParam("title") String title,
                                      @RequestParam("content") String content,
                                      @RequestParam(value = "attachments", required = false, defaultValue = "") String attachments,
                                      @RequestParam(value = "imgs", required = false, defaultValue = "") String imgs) {

        DataResponse response = new DataResponse();
        response.put("article", articleService.updateArticle(id, title, content, attachments, imgs));
        return response;
    }

    @RequestMapping("/delete")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse deleteArticle(Long id) {

        articleService.deleteArticle(id);
        return new DataResponse();
    }

}
