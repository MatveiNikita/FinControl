package md.crudproject.fincontrol.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

public record CreatedUserDto(@NotBlank
                             String username,
                             @Email
                             String email,
                             @NotBlank
                             String password,
                             @NotBlank
                             @CreationTimestamp
                             LocalDate dateOfBirth) {
}
