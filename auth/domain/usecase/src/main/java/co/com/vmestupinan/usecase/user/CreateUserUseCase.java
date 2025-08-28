package co.com.vmestupinan.usecase.user;

import java.math.BigDecimal;

import co.com.vmestupinan.model.user.User;
import co.com.vmestupinan.model.user.gateways.UserRepository;
import co.com.vmestupinan.usecase.user.exception.EmailAlreadyExistsException;
import co.com.vmestupinan.usecase.user.exception.InvalidSalaryException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public Mono<User> execute(User user) {
        return userRepository.existsByEmail(user.getEmail())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new EmailAlreadyExistsException("El correo ya está registrado"));
                    }
                    if (user.getBaseSalary().compareTo(BigDecimal.ZERO) < 0
                            || user.getBaseSalary().compareTo(new BigDecimal("15000000")) > 0) {
                        return Mono.error(new InvalidSalaryException("El salario base debe estar entre 0 y 15,000,000"));
                    }
                    return userRepository.save(user);
                });
    }
}
