package hu.unideb.fitbase.service.api.exception;

public class OperationFailedException extends Exception {

	private static final long serialVersionUID = -8374021929958845315L;

	public OperationFailedException(String message) {
		super(message);
	}

	public OperationFailedException(Throwable cause) {
		super(cause);
	}

}
