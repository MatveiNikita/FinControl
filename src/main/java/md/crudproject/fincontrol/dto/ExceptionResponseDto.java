package md.crudproject.fincontrol.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ExceptionResponseDto(
        LocalDate timestamp,
        String status,
        String message,
        String URL
) {
}
