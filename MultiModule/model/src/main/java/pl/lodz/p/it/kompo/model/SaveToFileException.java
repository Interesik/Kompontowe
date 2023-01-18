package pl.lodz.p.it.kompo.model;


public class SaveToFileException extends Exception{
    public SaveToFileException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }
}
