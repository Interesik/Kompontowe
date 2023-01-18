package pl.lodz.p.it.kompo.model;


public class ReadFromFileException extends Exception{
    public ReadFromFileException(String messageKey, Throwable cause) {
        super(messageKey, cause);
    }
}
