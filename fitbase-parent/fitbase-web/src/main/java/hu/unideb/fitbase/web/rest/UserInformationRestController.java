package hu.unideb.fitbase.web.rest;

import hu.unideb.fitbase.commons.pojo.response.LoginSuccesResponse;
import hu.unideb.fitbase.commons.pojo.response.MetaResponse;
import hu.unideb.fitbase.service.api.domain.FitBaseUser;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.web.token.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static hu.unideb.fitbase.commons.path.user.UserInfoPath.USER_INFO_URL;

@RestController
public class UserInformationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = USER_INFO_URL, method = RequestMethod.GET)
    public ResponseEntity<?> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        FitBaseUser user = (FitBaseUser) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.accepted().body(new LoginSuccesResponse(user.getUser(),new MetaResponse(null)));
    }
}