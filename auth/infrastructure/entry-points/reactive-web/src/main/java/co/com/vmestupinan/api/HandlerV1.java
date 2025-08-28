package co.com.vmestupinan.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import co.com.vmestupinan.api.dto.CreateUserDto;
import co.com.vmestupinan.api.mapper.UserMapper;
import co.com.vmestupinan.api.utils.ValidationUtils;
import co.com.vmestupinan.usecase.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerV1 {

    private final CreateUserUseCase userUseCase;
    private final UserMapper userMapper;

    //@PreAuthorize("hasRole('permissionGET')")
    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserDto.class)
                .doOnNext(ValidationUtils::validate)
                .map(userMapper::toModel)
                .flatMap(userUseCase::execute)
                .flatMap(user -> ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("Usuario creado correctamente")
                );
    }
}
