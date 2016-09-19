package cn.edu.cqupt.wyglzx.controller.outer.v1;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.OutputEntityJsonView;
import cn.edu.cqupt.wyglzx.service.SuggestionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cc on 16/9/5.
 */
@RestController("Public.SuggestionController")
@RequestMapping("/api/public/v1/suggestion")
@Component
public class SuggestionController {

    @Autowired
    SuggestionService suggestionService;

    @RequestMapping("/list")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getSuggestionList(@RequestParam(value = "type", required = false, defaultValue = "0") int type, @RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {

        DataResponse response = new DataResponse();
        response.put("suggestion_list", suggestionService.getSuggestionList(type, page, 3));
        response.put("count", suggestionService.getCount(type));
        return response;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse addSuggestion(@RequestParam("user_name") String userName,
                                      @RequestParam("user_account") String account,
                                      @RequestParam("type") Integer type,
                                      @RequestParam("content") String content,
                                      HttpServletRequest request) {

        String ip = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }

        return new DataResponse().put("suggestion", suggestionService.addSuggestion(content, type, userName, account, ip));
    }

}
