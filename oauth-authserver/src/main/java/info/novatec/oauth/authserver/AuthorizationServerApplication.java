package info.novatec.oauth.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Authorization server.
 */
@EnableAuthorizationServer
@EnableResourceServer
@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

    /* Uncomment following line to enable login form */
    /*
    @Order(2)
    @Configuration
    static class LoginConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.formLogin();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.parentAuthenticationManager(authenticationManager);
        }
    }
    */

    /* Uncomment the following block to switch from standard access tokens to signed JWT. */
    /*
    @Bean
    public AuthorizationServerConfigurer authorizationServerConfigurer(
            AuthenticationManager authenticationManager, OAuth2ClientProperties oAuth2ClientProperties) {
        return new AuthorizationServerConfigurer() {
            @Override
            public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
                security.tokenKeyAccess("permitAll()");
                security.checkTokenAccess("isAuthenticated()");
            }

            @Override
            public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
                clients.inMemory()
                        .withClient(oAuth2ClientProperties.getClientId())
                        .secret(oAuth2ClientProperties.getClientSecret())
                        .scopes("demo-app")
                        .autoApprove(true)
                        .authorizedGrantTypes("authorization_code","password","refresh");
            }

            @Override
            public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
                endpoints.accessTokenConverter(jwtAccessTokenConverter());
                endpoints.tokenEnhancer(jwtAccessTokenConverter());
                endpoints.tokenStore(tokenStore(jwtAccessTokenConverter()));
                endpoints.authenticationManager(authenticationManager);
            }
        };
    }

	@Bean
	public TokenStore tokenStore(JwtAccessTokenConverter tokenConverter) {
		return new JwtTokenStore(tokenConverter);
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		return new JwtAccessTokenConverter();
	}*/

}
