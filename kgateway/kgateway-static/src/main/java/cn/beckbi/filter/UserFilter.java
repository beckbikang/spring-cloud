package cn.beckbi.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.ResponseData;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-08-21 20:29
 */
@Slf4j
@Component
public class UserFilter implements GlobalFilter, Ordered {

    @Builder
    @Data
    static class Resp {
        private int code;
        private String msg;
    }

    private static final String BAD_CID = "123";

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public int getOrder(){
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();

        boolean matchFilter = false;

        if (Objects.nonNull(queryParams) && Objects.nonNull(queryParams.get("cid"))) {
            String cid = queryParams.get("cid").get(0);
            if (Objects.nonNull(cid) && BAD_CID.equals(cid)) {
                matchFilter = true;
            }
        }






        if (matchFilter) {
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            Resp resp = Resp.builder()
                    .code(401)
                    .msg("非法请求")
                    .build();
            DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(
                    this.getJsonBytes(resp)
            );
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            serverHttpResponse.getHeaders().add("Content-Type", "application/json; charset=utf-8");


            return serverHttpResponse.writeWith(Mono.just(dataBuffer));
        }

        return chain.filter(exchange);
    }

    private byte[] getJsonBytes(Object o) {
        try {
            return mapper.writeValueAsBytes(o);
        }catch (JsonProcessingException e) {
            log.error("json error", e);
        }
        return "".getBytes();
    }


}
