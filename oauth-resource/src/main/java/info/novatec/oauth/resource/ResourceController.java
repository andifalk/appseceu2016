package info.novatec.oauth.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for resource server.
 */
@RestController
public class ResourceController {

    @RequestMapping(path = "/myservice")
    public String myService() {
        return "The restricted resource artifact";
    }

    @RequestMapping(path = "/info")
    public String info() {
        return "This is the restricted resource server to access";
    }

}
