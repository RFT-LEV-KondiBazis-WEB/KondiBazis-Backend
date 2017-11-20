package hu.unideb.fitbase.service.api.exception;


import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = -4092192752587731891L;

	public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
