package ar.edu.dsi.gpstracker.exception;

import ar.edu.dsi.gpstracker.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiKeyInvalidException.class)
    public ResponseEntity<ErrorResponseDto> handleApiKeyInvalid(ApiKeyInvalidException ex) {
        return build(HttpStatus.UNAUTHORIZED, "API_KEY_INVALIDA", ex.getMessage());
    }

    @ExceptionHandler(BolsinNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleBolsinNotFound(BolsinNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, "BOLSIN_NO_ENCONTRADO", ex.getMessage());
    }

    @ExceptionHandler(CommissionCodeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleCommissionMismatch(CommissionCodeMismatchException ex) {
        return build(HttpStatus.NOT_FOUND, "CODIGO_COMISION_NO_COINCIDE", ex.getMessage());
    }

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MissingRequestHeaderException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<ErrorResponseDto> handleBadRequest(Exception ex) {
        return build(HttpStatus.BAD_REQUEST, "SOLICITUD_INVALIDA", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnexpected(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR_INTERNO", "Ocurrio un error interno inesperado.");
    }

    private ResponseEntity<ErrorResponseDto> build(HttpStatus status, String errorCode, String message) {
        ErrorResponseDto body = new ErrorResponseDto(
                errorCode,
                message,
                status.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(status).body(body);
    }
}
