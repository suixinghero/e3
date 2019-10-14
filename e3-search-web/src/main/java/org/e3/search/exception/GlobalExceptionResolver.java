package org.e3.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**全局异常处理器
 * @author xujin
 * @package-name org.e3.search.exception
 * @createtime 2019-10-13 19:36
 */

public class GlobalExceptionResolver implements HandlerExceptionResolver {
    private static final Logger logger=LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception e) {
        //打印控制台
        e.printStackTrace();
        //写日志
        logger.error("系统出现异常",e);
        //发邮件，发短信
        //给用户展现一个友好的页面
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error/exception");
        return modelAndView;
    }
}
