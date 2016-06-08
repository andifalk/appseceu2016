package info.novatec.oauth.authserver;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

/**
 * Rest controller for authorization server..
 */
@RestController
public class AuthServerController {

    @RequestMapping ("/")
    public String home() {
        return "This is the authorization server";
    }

    @RequestMapping("/user")
    public Principal user( Principal user) {
        return user;
    }
}
