package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.exceptions.ViolationException;
import hu.unideb.fitbase.commons.pojo.request.PassCreateRequest;
import hu.unideb.fitbase.service.api.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static hu.unideb.fitbase.commons.path.pass.create.PassPath.PASS_CREATE_URL;
import static hu.unideb.fitbase.commons.path.pass.create.PassPath.PASS_DELETE_URL;
import static hu.unideb.fitbase.commons.path.pass.create.PassPath.PASS_MODIFICATION_URL;

@RestController
public class PassRestController {

    @Autowired
    private PassService passService;

    @RequestMapping(value = PASS_CREATE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createPass(@RequestBody PassCreateRequest passCreateRequest) throws ViolationException {
        return null;
    }

    @RequestMapping(value = PASS_MODIFICATION_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> modificationPass(@RequestBody PassCreateRequest passCreateRequest) throws ViolationException {
        return null;
    }

    @RequestMapping(value = PASS_DELETE_URL, method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> deletePass(@RequestBody PassCreateRequest passCreateRequest) throws ViolationException {
        return null;
    }

}
