package info.novatec.security.header.config;

import java.net.URI;

import info.novatec.security.header.csp.ContentSecurityPolicyHeadersWriter;

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

        // Disable HSTS when using self signed certificate and no root CA is imported in browser for this.
        // http.headers().httpStrictTransportSecurity().disable();

        //http.headers ().disable ();

        // http.headers ().xssProtection ().xssProtectionEnabled ( false ).block ( false );

        // Standard CSP header implementation
        http.headers ().contentSecurityPolicy ( "default-src 'self'; report-uri /report/" );

        // Custom implementation for CSP header
/*
        http.headers ()
        .addHeaderWriter (
                ContentSecurityPolicyHeadersWriter
                        .create ()
                        .defaultSource ( "'self'" )
                        .reportUri ( new URI ( "https://localhost:9099/report" ) )
                        .reportOnly () );
*/

        // Header for HTTP public key pinning
/*
        http.headers ().httpPublicKeyPinning ().addSha256Pins ( "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAqIlIK+0HWok4D9j94FqG" +
                "e9xf7HvfpmwisK7gFTwVsrlGU7JGVmPRNdV3RuQl2KFpzwtfmXm0qlwSmsMmkgYq" +
                "o+IyVYMziQGjZpyHxlg5d96sGTtU4Y+PLSkQJuPQT01ZoQlMv1wbQ/SpDszgveTV" +
                "y8WThygMtrN6AcSxYpxk1XBZ6eGsVZD1rgWaIlpfHAPBhH7DALuUDMKf8YYpcYVr" +
                "ViedVMojoAEf1XAE4fyeL3kBBpoKXi5uC/1HPxX8SPphzfblXW1nDKK7EaDJZxxT" +
                "qTCA0IK4MCcMXhPaVdEfbpX6fJo8RvXPBxYAORhq92FPS3VUIDDxrQSQdWegADAk" +
                "HuixJuUikBO92YtXDSulYCQApOcku0A7vdZ5jMrAr7jbbgyQtZxVcXnEPYTXJOC5" +
                "fkqfNy3dzqTvfour4sw+cClt/UdgimwOWrKIuEl/SOsaaouQsafPShN4pye2CAe3" +
                "MqoGQezJMEsbjyEaluH6myDkmMEkJZGXZL9tZX+A4K7vkZYS4aOI3ol1OlmE2fgY" +
                "h6ANEFFI0ewoW76rvKxI/Ekc2EQW2SeDnXTgOxxG3daJMw+h+tip56dxm9qrwGrO" +
                "qwbgzx6/L7D5UodTsNnaqc1ly94ed4qm9MTcErUh8qKzhdSSYOmtemX+iMpPUwFE" +
                "i9/mEYI2VC+3OmYcVswP42cCAwEAAQ==" ).reportUri ( "http://example.net/pkp-report" ).reportOnly ( true );
*/

        http.formLogin ().permitAll ()
        .and ()
        .authorizeRequests ()
        .antMatchers ( "/report" ).permitAll ()
        .antMatchers ( "/" ).hasRole ( "USER" )
        .antMatchers ( "/admin" ).hasRole ( "ADMIN" )
        .anyRequest ().authenticated ();
    }

}
