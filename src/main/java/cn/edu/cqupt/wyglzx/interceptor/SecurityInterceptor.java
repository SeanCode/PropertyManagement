package cn.edu.cqupt.wyglzx.interceptor;

import cn.edu.cqupt.wyglzx.common.Config;
import cn.edu.cqupt.wyglzx.exception.ParamNotSetException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class SecurityInterceptor implements HandlerInterceptor
{

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(SecurityInterceptor.class);


    private boolean canGuestAccess(String path)
    {
        for (int i = 0; i < Config.PATH_GUEST_CAN_ACCESS.length; i++)
        {
            if (Config.PATH_GUEST_CAN_ACCESS[i].equals(path))
            {
                return  true;
            }
        }

        return false;
    }

    private String getRequiredParam(HttpServletRequest httpServletRequest, String key) throws ParamNotSetException {
        if (!httpServletRequest.getParameterMap().containsKey(key))
        {
            throw new ParamNotSetException(key);
        }

        return httpServletRequest.getParameter(key);
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception
    {
        URI uri = new URI(httpServletRequest.getRequestURI());
        String path = uri.getPath().substring(1);

//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
//        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Set-Cookie");

        logger.trace(String.format("path: %s", path));

        if (!canGuestAccess(path))
        {

        }

        return true;
    }




    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception
    {

    }
}
