package co.com.vmestupinan.r2dbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;

import co.com.vmestupinan.model.user.User;
import co.com.vmestupinan.r2dbc.entity.UserEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class MyReactiveRepositoryAdapterTest {

    @InjectMocks
    UserReactiveRepositoryAdapter repositoryAdapter;
    @Mock
    UserReactiveRepository repository;
    @Mock
    ObjectMapper mapper;

    @Test
    void mustFindValueById() {
        when(repository.findById(1L)).thenReturn(Mono.just(mock(UserEntity.class)));
        when(mapper.map(mock(UserEntity.class), User.class)).thenReturn(mock(User.class));
        Mono<User> result = repositoryAdapter.findById(1L);
        StepVerifier.create(result)
                .expectNextMatches(value -> value.equals(mock(User.class)))
                .verifyComplete();
    }
}
