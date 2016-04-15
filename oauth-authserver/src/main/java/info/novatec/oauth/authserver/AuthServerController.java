package info.novatec.oauth.authserver;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for authorization server..
 */
@RestController
public class AuthServerController {

    @RequestMapping ("/info")
    public String home() {
        return "This is the authorization server";
    }

    @RequestMapping("/user")
    public Principal user( Principal user) {
        return user;
    }
}
