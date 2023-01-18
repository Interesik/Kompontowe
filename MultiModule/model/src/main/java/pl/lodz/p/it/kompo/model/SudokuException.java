package pl.lodz.p.it.kompo.model;

import java.util.Locale;
import java.util.ResourceBundle;

public class SudokuException extends RuntimeException {
    public SudokuException(Throwable cause) {
        super(cause);
    }

    public SudokuException(String messageKey, Locale locale) {
        super(ResourceBundle.getBundle("ResourceBundle", locale)
                .getString(messageKey));
    }

    public SudokuException(String message, Throwable cause) {
        super(message, cause);
    }

    public SudokuException(String messageKey, Throwable cause, Locale locale) {
        super(ResourceBundle.getBundle("ResourceBundle", locale)
                .getString(messageKey),cause);
    }
}
