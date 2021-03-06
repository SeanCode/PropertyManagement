package cn.edu.cqupt.wyglzx.controller.inner.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.SuggestionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 16/9/5.
 */
@RestController("Private.SuggestionController")
@RequestMapping("/api/private/v1/suggestion")
@Component
public class SuggestionController {

    @Autowired
    SuggestionService suggestionService;

    @RequestMapping("/list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getSuggestionList(@RequestParam(value = "type", required = false, defaultValue = "0") int type, @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {

        DataResponse response = new DataResponse();
        response.put("suggestion_list", suggestionService.getSuggestionList(type, page, 10));
        response.put("count", suggestionService.getCount(type));
        return response;
    }

    @RequestMapping("/list-by-status")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getSuggestionListByStatus(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page, @RequestParam("status") Integer status) {

        DataResponse response = new DataResponse();
        response.put("suggestion_list", suggestionService.getSuggestionListByStatus(status, page));
        response.put("count", suggestionService.getCountByStatus(status));
        return response;
    }

    @RequestMapping("/reply")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse addReply(@RequestParam("reply") String reply, @RequestParam("id") Long id) {

        suggestionService.addReply(id, reply);
        return new DataResponse();
    }

}
