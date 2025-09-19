package co.com.vmestupinan.usecase.user;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import co.com.vmestupinan.model.user.User;
import co.com.vmestupinan.model.user.gateways.UserRepository;
import co.com.vmestupinan.usecase.user.exception.EmailAlreadyExistsException;
import co.com.vmestupinan.usecase.user.exception.InvalidSalaryException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id(1L)
                .name("Juan")
                .lastName("Pérez")
                .idNumber("123456789")
                .birthDate(LocalDate.of(1990, 1, 1))
                .address("Calle Falsa 123")
                .phone("3001234567")
                .email("juan@test.com")
                .baseSalary(new BigDecimal("5000000"))
                .build();
    }

    @Test
    void shouldCreateUserWhenEmailDoesNotExistAndSalaryIsValid() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(Mono.just(false));
        when(userRepository.save(user)).thenReturn(Mono.just(user));

        StepVerifier.create(createUserUseCase.execute(user))
                .verifyComplete();

        verify(userRepository).existsByEmail(user.getEmail());
        verify(userRepository).save(user);
    }

    @Test
    void shouldThrowErrorWhenEmailAlreadyExists() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(Mono.just(true));

        StepVerifier.create(createUserUseCase.execute(user))
                .expectErrorMatches(throwable -> throwable instanceof EmailAlreadyExistsException
                && throwable.getMessage().contains("El correo ya está registrado"))
                .verify();

        verify(userRepository).existsByEmail(user.getEmail());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowErrorWhenSalaryIsInvalid() {
        user.setBaseSalary(new BigDecimal("-1000"));
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(Mono.just(false));

        StepVerifier.create(createUserUseCase.execute(user))
                .expectErrorMatches(throwable -> throwable instanceof InvalidSalaryException
                && throwable.getMessage().contains("El salario base debe estar entre 0 y 15,000,000"))
                .verify();

        verify(userRepository).existsByEmail(user.getEmail());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowErrorWhenSalaryIsTooHigh() {
        user.setBaseSalary(new BigDecimal("20000000"));
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(Mono.just(false));

        StepVerifier.create(createUserUseCase.execute(user))
                .expectErrorMatches(throwable -> throwable instanceof InvalidSalaryException
                && throwable.getMessage().contains("El salario base debe estar entre 0 y 15,000,000"))
                .verify();

        verify(userRepository).existsByEmail(user.getEmail());
        verify(userRepository, never()).save(any());
    }
}
