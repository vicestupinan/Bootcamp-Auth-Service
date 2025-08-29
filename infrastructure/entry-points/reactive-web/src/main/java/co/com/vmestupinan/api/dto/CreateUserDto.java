package co.com.vmestupinan.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para la creación de un usuario")
public record CreateUserDto(
        @Schema(description = "Nombre del usuario", example = "Juan")
        @NotBlank(message = "El nombre es requerido")
        String name,
        @Schema(description = "Apellido del usuario", example = "Perez")
        @NotBlank(message = "El apellido es requerido")
        String lastName,
        @Schema(description = "Número de identificación del usuario", example = "1234567890")
        @NotBlank(message = "El número de identificación es requerido")
        String idNumber,
        @Schema(description = "Fecha de nacimiento del usuario", example = "2000-01-01")
        @NotNull(message = "La fecha de nacimiento es requerida")
        LocalDate birthDate,
        @Schema(description = "Dirección del usuario", example = "Calle 123")
        @NotBlank(message = "La dirección es requerida")
        String address,
        @Schema(description = "Número de teléfono del usuario", example = "1234567890")
        @NotBlank(message = "El número de teléfono es requerido")
        String phone,
        @Schema(description = "Correo electrónico del usuario", example = "test@mail.com")
        @NotBlank(message = "El correo electrónico es requerido")
        @Email(message = "El correo electrónico no es válido")
        String email,
        @Schema(description = "Salario base del usuario", example = "1000000")
        @NotNull(message = "El salario base es requerido")
        BigDecimal baseSalary
        ) {

}
