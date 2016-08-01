package cn.edu.cqupt.wyglzx.controller.outer.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.ArticleService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/7/30.
 */
@RestController("Public.ArticleController")
@RequestMapping("/api/public/v1/article")
@Component
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getArticleList() {

        return new DataResponse().put("article", "list");
    }

    @RequestMapping("/latest")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getLatest() {

        return new DataResponse().put("article_list", articleService.getLatest());
    }

    @RequestMapping("/guide-process-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getGuideProcessList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getGuideProcessList(page));
    }

    @RequestMapping("/guide-work-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getGuideWorkList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getGuideWorkList(page));
    }

    @RequestMapping("/guide-rule-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getGuideRuleList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getGuideRuleList(page));
    }

    @RequestMapping("/guide-download-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getGuideDownloadList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getGuideDownloadList(page));
    }

    @RequestMapping("/news-hot-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getNewsHotList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getNewsHotList(page));
    }

    @RequestMapping("/news-post-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getNewsPostList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getNewsHotList(page));
    }

    @RequestMapping("/log-device-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getLogDeviceList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getLogDeviceList(page));
    }

    @RequestMapping("/log-teaching-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getLogTeachingList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getLogTeachingList(page));
    }

    @RequestMapping("/log-department-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getLogDepartmentList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getLogDepartmentList(page));
    }

    @RequestMapping("/log-property-list")
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse getLogPropertyList(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {

        return new DataResponse().put("article_list", articleService.getLogPropertyList(page));
    }

}
