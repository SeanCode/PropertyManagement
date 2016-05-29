package cn.edu.cqupt.wyglzx.controller.exception;

import cn.edu.cqupt.wyglzx.common.DataResponse;
import cn.edu.cqupt.wyglzx.common.ErrorResponse;
import cn.edu.cqupt.wyglzx.exception.BaseException;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by cc on 9/7/15.
 */
@Controller
public class AppErrorController implements ErrorController {

    private static final String PATH_ERROR = "/error";

    /**
     * Supports the HTML Error View
     *
     * @param request model
     * @return model view
     */
    @RequestMapping(value = PATH_ERROR, produces = "text/html")
    public String errorHtml(HttpServletRequest request, Model model) {

        HttpStatus status = getStatus(request);
        model.addAttribute("message", status.value() + ": " + status.getReasonPhrase());
        return "error";
    }

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

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return parameter != null && !"false".equals(parameter.toLowerCase());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ignored) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
