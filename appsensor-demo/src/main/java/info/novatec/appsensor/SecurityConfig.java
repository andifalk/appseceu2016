package info.novatec.appsensor;

import org.owasp.appsensor.integration.springsecurity.context.AppSensorSecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * Created by afa on 15.04.16.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new AppSensorSecurityContextRepository ();
    }

    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http.authorizeRequests().anyRequest ().fullyAuthenticated ();
        http.formLogin().permitAll ();
        http.securityContext().securityContextRepository(securityContextRepository());
    }
}
