package co.com.vmestupinan.r2dbc.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table("_user")
public class UserEntity {

    @Id
    @Column("user_id")
    private Long id;
    private String name;

    @Column("last_name")
    private String lastName;

    @Column("id_number")
    private String idNumber;

    @Column("birth_date")
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String email;

    @Column("base_salary")
    private BigDecimal baseSalary;
}
