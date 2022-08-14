package cn.beckbi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: spring-cloud
 * @description: SpecialPathFilter
 * @author: bikang
 * @create: 2022-08-13 11:35
 */
@Slf4j
public class ErrorFilter extends ZuulFilter {



    public ErrorFilter() {
        super();
    }

    @Override
    public boolean shouldFilter(){
        return true;
    }

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public Object run() {
        RequestContext rtx = RequestContext.getCurrentContext();
        String path = rtx.getRequest().getRequestURI();
        log.error("path="+path);
        Throwable throwable = rtx.getThrowable();
        log.error("Filter Error:{}", throwable.getCause().getMessage());
        return null;
    }

}
