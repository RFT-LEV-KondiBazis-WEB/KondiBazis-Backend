package hu.unideb.fitbase.service.api.startup;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
class AdministratorUserDetailsHolder {

	@Resource(lookup = "java:global/fitbase.admin.username")
	private String username;

	@Resource(lookup = "java:global/fitbase.admin.password")
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
