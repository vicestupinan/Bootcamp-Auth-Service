package co.com.vmestupinan.r2dbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class UserRepositoryAdapterTest {

    @InjectMocks
    UserReactiveRepositoryAdapter repositoryAdapter;
    @Mock
    UserReactiveRepository repository;
    @Mock
    ObjectMapper mapper;

    @Test
    void mustFindValueByEmail() {
        when(repository.existsByEmail("test@example.com"))
                .thenReturn(Mono.just(true));

        Mono<Boolean> result = repositoryAdapter.existsByEmail("test@example.com");

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();
    }
}
