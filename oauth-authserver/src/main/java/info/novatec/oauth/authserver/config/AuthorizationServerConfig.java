package info.novatec.oauth.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Configuration for authorization server.
 */
@EnableAuthorizationServer
@EnableResourceServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
}
