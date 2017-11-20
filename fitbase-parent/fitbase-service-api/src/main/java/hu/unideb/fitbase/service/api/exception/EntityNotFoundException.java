package hu.unideb.fitbase.service.api.exception;

public class EntityNotFoundException extends ServiceException {

	private static final long serialVersionUID = 8224046890259947925L;

	public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
