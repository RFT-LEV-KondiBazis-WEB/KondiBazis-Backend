package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.response.LoginSuccesResponse;
import hu.unideb.fitbase.commons.pojo.response.MetaResponse;
import hu.unideb.fitbase.service.api.domain.FitBaseUser;
import hu.unideb.fitbase.service.api.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static hu.unideb.fitbase.commons.path.user.UserInfoPath.USER_INFO_URL;

@RestController
public class UserInformationRestController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = USER_INFO_URL, method = RequestMethod.GET)
    public ResponseEntity<?> getAuthenticatedUser() {
        return ResponseEntity.accepted().body(new LoginSuccesResponse(getUser(), new MetaResponse(null)));
    }

    private User getUser() {
        return ((FitBaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}