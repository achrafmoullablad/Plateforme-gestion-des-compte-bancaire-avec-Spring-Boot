package gestion.compte.security;



import javax.sql.DataSource;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource datasource;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 auth.jdbcAuthentication().
	 dataSource(datasource).
	 usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?;")
	 .authoritiesByUsernameQuery("select user as principal, role as role  from user_roles where user=?").
	 rolePrefix("ROLE_").passwordEncoder(new BCryptPasswordEncoder());
	 
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		//http.requestCache().requestCache(new NullRequestCache());
	}
}
