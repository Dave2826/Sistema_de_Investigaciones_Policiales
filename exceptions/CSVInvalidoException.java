package exceptions;

public class CSVInvalidoException extends Exception {
    public CSVInvalidoException(String message) {
        super(message);
    }
}
// Se agrego para que el programa no se caiga cuando se intente leer un archivo
// CSV mal formado