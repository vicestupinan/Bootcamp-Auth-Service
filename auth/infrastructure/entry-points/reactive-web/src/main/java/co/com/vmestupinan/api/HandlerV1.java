package co.com.vmestupinan.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.vmestupinan.api.dto.CreateUserDto;
import co.com.vmestupinan.api.mapper.UserMapper;
import co.com.vmestupinan.api.utils.ValidationUtils;
import co.com.vmestupinan.usecase.user.UserUseCase;
import co.com.vmestupinan.usecase.user.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerV1 {

    private final UserUseCase userUseCase;
    private final UserMapper userMapper;

    //@PreAuthorize("hasRole('permissionGET')")
    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserDto.class)
                .doOnNext(ValidationUtils::validate)
                .map(userMapper::toModel)
                .flatMap(userUseCase::create)
                .flatMap(user -> ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("Usuario creado correctamente")
                )
                .onErrorResume(IllegalArgumentException.class, e
                        -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of("error", e.getMessage()))
                )
                .onErrorResume(BusinessException.class, e
                        -> ServerResponse.status(HttpStatus.CONFLICT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of("error", e.getMessage()))
                );
    }

    // @PreAuthorize("hasRole('permissionGETOther')")
    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }

    // @PreAuthorize("hasRole('permissionPOST')")
    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        // useCase.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
