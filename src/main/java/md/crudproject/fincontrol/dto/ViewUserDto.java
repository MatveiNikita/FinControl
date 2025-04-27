package md.crudproject.fincontrol.dto;

import lombok.Data;
import md.crudproject.fincontrol.model.User;

import java.time.LocalDate;

public record ViewUserDto(String username,
        String email,
        LocalDate dateOfBirth) {
}
