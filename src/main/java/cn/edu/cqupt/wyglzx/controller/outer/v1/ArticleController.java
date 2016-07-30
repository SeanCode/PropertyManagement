package cn.edu.cqupt.wyglzx.controller.outer.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/7/30.
 */
@RestController
@RequestMapping("/api/public/v1/article")
public class ArticleController {

    @RequestMapping("/list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getArticleList() {

        return new DataResponse().put("article", "list");
    }
}
