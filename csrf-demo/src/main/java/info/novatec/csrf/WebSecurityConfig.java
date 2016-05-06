package info.novatec.csrf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AFA on 06.05.2016.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http
                //.csrf ().disable ()
                .csrf ().requireCsrfProtectionMatcher (
                    request -> RequestMethod.POST.name ().equals (request.getMethod ())
                            || ( request.getRequestURI () != null && request.getRequestURI ().contains ( "create" )) );
                http.authorizeRequests ().anyRequest ().fullyAuthenticated ()
                .and ()
                .httpBasic ().disable ()
                .formLogin ().permitAll ()
                .and ()
                .logout ().permitAll ();
    }
}
