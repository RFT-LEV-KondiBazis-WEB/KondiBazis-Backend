package hu.unideb.fitbase.service.api.service;

import hu.unideb.fitbase.commons.pojo.exceptions.BaseException;
import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.service.api.domain.Gym;
import hu.unideb.fitbase.service.api.domain.Pass;
import hu.unideb.fitbase.service.api.exception.ServiceException;

import java.util.List;

public interface PassService {

    Pass addPass(PassCreateRequest pass) throws BaseException;

    Pass findPassById(Long id) throws BaseException;

    void deletePass(Long id) throws BaseException;

    List<Pass> findByGymIdAllPasses(Long gym);

    Long countPasses();

    Pass update(Pass pass)throws BaseException;

    Pass findPassByName(String name) throws BaseException;
}
