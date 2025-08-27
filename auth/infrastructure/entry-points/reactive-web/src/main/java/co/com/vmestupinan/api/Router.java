package co.com.vmestupinan.api;

import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.vmestupinan.api.dto.CreateUserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Configuration
public class Router {

    @Bean
    @RouterOperation(
        path = "/api/v1/users",
        produces = { MediaType.APPLICATION_JSON_VALUE },
        method = RequestMethod.POST,
        beanClass = HandlerV1.class,
        beanMethod = "createUser",
        operation = @Operation(
            operationId = "createUser",
            summary = "Crear usuario",
            description = "Crea un nuevo usuario en el sistema",
            tags = {"User"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(schema = @Schema(implementation = CreateUserDto.class))
            ),
            responses = {
                @ApiResponse(responseCode = "201", description = "Usuario creado",
                    content = @Content(schema = @Schema(implementation = CreateUserDto.class))),
                @ApiResponse(responseCode = "400", description = "Datos inválidos"),
                @ApiResponse(responseCode = "409", description = "Conflicto de negocio")
            }
        )
    )
    
    public RouterFunction<ServerResponse> routerFunction(HandlerV1 handlerV1) {
        return RouterFunctions
                .route()
                .path("/api/v1", builder -> builder
                    .POST("/users", handlerV1::createUser)
                )
                .build();
    }
}
