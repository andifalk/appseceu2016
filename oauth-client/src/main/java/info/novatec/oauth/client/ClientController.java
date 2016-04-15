package info.novatec.oauth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for SSO client.
 */
@RestController
public class ClientController {

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @RequestMapping(path = "/service")
    public String home() {
        return restTemplate.getForObject ( "http://localhost:8082/resource/myservice", String.class );
    }

    @RequestMapping(path = "/info")
    public String info() {
        return "This is the SSO client";
    }
}
