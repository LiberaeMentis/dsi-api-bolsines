package ar.edu.dsi.gpstracker.exception;

public class BolsinNotFoundException extends RuntimeException {
    public BolsinNotFoundException(Integer numeroBolsin) {
        super("No se encontro un bolsin con numero " + numeroBolsin + ".");
    }
}
