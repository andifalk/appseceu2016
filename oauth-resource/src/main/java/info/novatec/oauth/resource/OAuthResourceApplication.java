package info.novatec.oauth.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Resource server.
 */
@EnableResourceServer
@SpringBootApplication
public class OAuthResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run( OAuthResourceApplication.class, args);
	}
}
