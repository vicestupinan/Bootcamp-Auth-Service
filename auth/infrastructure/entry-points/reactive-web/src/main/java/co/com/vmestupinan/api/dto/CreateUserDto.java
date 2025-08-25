package co.com.vmestupinan.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        @NotBlank(message = "El nombre es requerido")
        String name,
        @NotBlank(message = "El apellido es requerido")
        String lastName,
        @NotBlank(message = "El número de identificación es requerido")
        String idNumber,
        @NotNull(message = "La fecha de nacimiento es requerida")
        LocalDate birthDate,
        @NotBlank(message = "La dirección es requerida")
        String address,
        @NotBlank(message = "El número de teléfono es requerido")
        String phone,
        @NotBlank(message = "El correo electrónico es requerido")
        @Email(message = "El correo electrónico no es válido")
        String email,
        @NotNull(message = "El salario base es requerido")
        BigDecimal baseSalary
        ) {

}
