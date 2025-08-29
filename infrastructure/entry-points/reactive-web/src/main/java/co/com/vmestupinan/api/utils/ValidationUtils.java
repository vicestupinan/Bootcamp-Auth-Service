package co.com.vmestupinan.api.utils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import co.com.vmestupinan.api.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import reactor.core.publisher.Mono;

public class ValidationUtils {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> Mono<T> validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            Map<String, String> fieldErrors = violations.stream()
                    .collect(Collectors.toMap(
                            v -> v.getPropertyPath().toString(),
                            ConstraintViolation::getMessage
                    ));
            return Mono.error(new ValidationException(fieldErrors));
        }
        return Mono.just(object);
    }
}
