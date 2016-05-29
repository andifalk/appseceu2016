package info.novatec.oauth.social;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Sso
@SpringBootApplication
public class OAuthSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run( OAuthSocialApplication.class, args);
	}

	@RequestMapping(path = "/")
	public String index() {
        OAuth2Authentication details = (OAuth2Authentication) SecurityContextHolder.getContext ().getAuthentication ();
		Map userDetails = (Map) details.getUserAuthentication ().getDetails ();
		return String.format ( "Authenticated user: %s (%s)", userDetails.get ( "name" ), userDetails.get ( "email" ) );
	}
}
