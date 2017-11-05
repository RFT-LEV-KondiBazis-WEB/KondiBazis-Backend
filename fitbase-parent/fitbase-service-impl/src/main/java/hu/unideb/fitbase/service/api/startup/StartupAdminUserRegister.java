package hu.unideb.fitbase.service.api.startup;

import hu.unideb.fitbase.commons.pojo.enumeration.UserRole;
import hu.unideb.fitbase.service.api.domain.User;
import hu.unideb.fitbase.service.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
class StartupAdminUserRegister {

	@Autowired
	private UserService userService;

	@Autowired
	private AdministratorUserDetailsHolder administratorUserDetailsHolder;

	private static final Logger LOGGER = LoggerFactory.getLogger(StartupAdminUserRegister.class);

	@PostConstruct
	public void registerAdmin() {
		Date date = new Date();
		date.getTime();
		if (!userService.containsAny()) {
			User adminUser = User.builder()
					.username(administratorUserDetailsHolder.getUsername())
					.password(new BCryptPasswordEncoder().encode(administratorUserDetailsHolder.getPassword()))
					.lastPasswordResetDate(new Date(date.getTime()))
					.userRole(UserRole.ADMIN)
					.enabled(true)
					.build();
			userService.save(adminUser);
			LOGGER.info("Admin user registred");
		} else {
			LOGGER.info("There is at least one existing user, no admin registered");
		}
	}

}
