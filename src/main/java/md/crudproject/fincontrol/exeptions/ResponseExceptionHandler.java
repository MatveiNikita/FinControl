package md.crudproject.fincontrol.exeptions;

import jakarta.servlet.http.HttpServletRequest;
import md.crudproject.fincontrol.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponseDto> handResponseException(ResponseStatusException responseException,
                                                                      HttpServletRequest request){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                LocalDate.now(),
                responseException.getStatusCode().toString(),
                responseException.getReason(),
                request.getRequestURI()
        );
        return ResponseEntity.status(responseException.getStatusCode()).body(exceptionResponseDto); //Насколько логично возрващать еще раз код ошибки, если в ExceptionResponseDto он и так есть
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleCommonException(RuntimeException ex, HttpServletRequest request){

        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                LocalDate.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ex.getMessage() != null ? ex.getMessage() : "We don't know what it is. If we knew what it is, but we don't know what it is",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDto);
    }
}
