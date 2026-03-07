package ar.edu.dsi.gpstracker.exception;

public class ApiKeyInvalidException extends RuntimeException {
    public ApiKeyInvalidException(String message) {
        super(message);
    }
}
