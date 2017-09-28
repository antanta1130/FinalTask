package exceptions;

public class CSVReadingException extends RuntimeException {
    public CSVReadingException(String message) {
        super(message);
    }

}