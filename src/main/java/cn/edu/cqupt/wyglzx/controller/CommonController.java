package cn.edu.cqupt.wyglzx.controller;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.ErrorResponse;
import cn.edu.cqupt.wyglzx.exception.BaseException;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cc on 9/7/15.
 */
@RestController
public class CommonController implements ErrorController {
    private static final String PATH_ERROR = "/error";

    @RequestMapping(PATH_ERROR)
    public ErrorResponse error(HttpServletRequest request) {
        return new ErrorResponse(BaseException.ERROR, "an error occurred");
    }

    @RequestMapping("/error404")
    public ErrorResponse error404(HttpServletRequest request) {
        return new ErrorResponse(BaseException.ERROR_404, "not found");
    }

    @RequestMapping("/error500")
    public ErrorResponse error500(HttpServletRequest request) {
        return new ErrorResponse(BaseException.ERROR_500, "inner server error");
    }

    @RequestMapping("/test/md5")
    public DataResponse md5(@RequestParam("content") String content) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.put("md5", DigestUtils.md5DigestAsHex(content.getBytes()));
        return dataResponse;
    }

    @Override
    public String getErrorPath() {
        return PATH_ERROR;
    }
}
