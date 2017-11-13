package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.exception.ServiceException;

public interface PassService {

    Pass addPass(Pass pass)throws ViolationException, ServiceException;
}
