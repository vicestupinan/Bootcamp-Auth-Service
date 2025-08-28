package co.com.vmestupinan.api.exception;

import java.util.Map;

public class ValidationException extends RuntimeException {

    private final Map<String, String> fieldErrors;

    public ValidationException(Map<String, String> fieldErrors) {
        super("Hay campos inválidos");
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }
}
