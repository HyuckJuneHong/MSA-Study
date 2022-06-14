package kr.co.gatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


/*
    * yml에 등록하기 위한 Custom Filter
    1) spring cloud gateway 는 netty란 내장서버를 쓰고 있느 비동기 서버이다.
    2) 때문에, ServletRequest, ServletResponse이 아닌, ServerHttpRequest, ServerHttpResponse를 사용할 수 있다.
    3) 예제로, Pre filter에는 request id를 출력해 본다.
    4) 처리가 다 끝나면, Post filter를 적용 할 수 있다.
    5) 즉, response에는 statusCode를 출력하는 예시이다.
    결론) gateway yml에 적힌 모든 접속 경로는 CustomFilter를 거치게 된다.

    * Pre, Post에 순위는 Ordered를 통해 설정할 수 있고, yml에서 변수의 값을 설정할 수 있다.
    1) CustomFilter Class의 내장 class인 Config Class에 변수를 (preLogger, postLogger) 설정 변수를 생성한다.
    2) @Data 이용 시 config 변수 타입에 맞춰 메소드가 알아서 만들어진다.
 */
@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {

    public CustomFilter(){
        super(Config.class);
    }

    @Data
    public static class Config{
        //Put the config properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    /**
     * Custom Pre Filter
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(Config config) {
        GatewayFilter gatewayFilter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging filter baseMessage ->", config.getBaseMessage());
            if(config.isPreLogger()){
                log.info("Logging pre filter: request id -> ", request.getId());
            }

            //Custome post filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if(config.isPostLogger()){
                    log.info("Logging post filter: response cod -> ", response.getStatusCode());
                }
            }));
        }, Ordered.LOWEST_PRECEDENCE); //우선 순위 부여
        return gatewayFilter;
    }

}
