package info.novatec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Web security config.
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure ( AuthenticationManagerBuilder auth ) throws Exception {
        auth
                .inMemoryAuthentication ()
                .withUser ( "user" ).password ( "user" ).roles ( "USER" )
                .and ()
                .withUser ( "admin" ).password ( "admin" ).roles ( "USER", "ADMIN" )
                .and ()
                .withUser ( "anonymous" ).password ( "anonymous" ).roles ( "DUMMY" );
    }

    @Override
    protected void configure ( HttpSecurity http ) throws Exception {
        http
                .formLogin ().permitAll ()
                .and().logout().permitAll()
                .and ()
                .authorizeRequests ()
                .antMatchers ( "/" ).hasRole ( "USER" )
                .antMatchers ( "/admin" ).hasRole ( "ADMIN" )
                .anyRequest ().authenticated ();
    }
}
