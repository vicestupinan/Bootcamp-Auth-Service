package co.com.vmestupinan.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(UserHandlerV1 handlerV1) {
        return RouterFunctions
                .route()
                .path("/api/v1", builder -> builder
                .POST("/usecase/path", handlerV1::saveUser)
                .POST("/usecase/otherpath", handlerV1::listenPOSTUseCase)
                .GET("/otherusercase/path", handlerV1::listenGETOtherUseCase))
                .build();
    }
}
