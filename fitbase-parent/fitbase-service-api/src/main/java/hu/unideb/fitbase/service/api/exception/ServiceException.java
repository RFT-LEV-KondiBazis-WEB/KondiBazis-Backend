package hu.unideb.fitbase.service.api.exception;


import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;

public class ServiceException extends BaseException {

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
