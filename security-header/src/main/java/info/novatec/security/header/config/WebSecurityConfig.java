package info.novatec.security.header.config;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import info.novatec.security.header.csp.ContentSecurityPolicyHeadersWriter;

import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
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
                .headers ().xssProtection ().xssProtectionEnabled ( true ).block ( false ).and ()

/*
                .headers ()
                .addHeaderWriter (
                        ContentSecurityPolicyHeadersWriter
                                .create ()
                                .defaultSource ( "'self'" )
                                .reportUri ( new URI ( "https://localhost:9099/report" ) )
                                .reportOnly () )
*/
                .and ()
                .formLogin ().permitAll ()
                .and ()
                .authorizeRequests ()
                .antMatchers ( "/report" ).permitAll ()
                .antMatchers ( "/" ).hasRole ( "USER" )
                .antMatchers ( "/admin" ).hasRole ( "ADMIN" )
                .anyRequest ().authenticated ();
    }

}
