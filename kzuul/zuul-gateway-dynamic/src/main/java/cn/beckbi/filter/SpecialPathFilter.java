package cn.beckbi.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @program: spring-cloud
 * @description: SpecialPathFilter
 * @author: bikang
 * @create: 2022-08-13 11:35
 */
@Slf4j
public class SpecialPathFilter extends ZuulFilter {

    private List<String> forbidPath = Arrays.asList(
            "/user/666"
    );

    public SpecialPathFilter() {
        super();
    }

    @Override
    public boolean shouldFilter(){
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public Object run() {
        RequestContext rtx = RequestContext.getCurrentContext();
        String path = rtx.getRequest().getRequestURI();
        log.info("path="+path);
        if (forbidPath.contains(path)) {
            rtx.setSendZuulResponse(false);

            rtx.setResponseBody(
                    "{\"code\":-1,\"msg\":\"path is forbid\"}"
            );
            rtx.getResponse().setContentType(
                    "application/json;charset=utf-8"
            );
            return null;
        }
        return null;
    }

}
