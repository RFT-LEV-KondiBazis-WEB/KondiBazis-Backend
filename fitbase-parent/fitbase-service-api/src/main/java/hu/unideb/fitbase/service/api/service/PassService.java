package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.exception.ServiceException;

import java.util.List;

public interface PassService {

    Pass addPass(Pass pass) throws ViolationException, ServiceException;

    Pass findPassById(Long id) throws BaseException;

    void deletePass(Long id);

    List<Pass> findByGymIdAllPasses(Long gym);

    Pass update(Pass pass)throws ViolationException;

    Pass findPassByName(String name);

}
