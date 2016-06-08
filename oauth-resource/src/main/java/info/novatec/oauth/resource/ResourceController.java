package info.novatec.oauth.resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

/**
 * Rest controller for resource server.
 */
@RestController
public class ResourceController {

    @Secured("ROLE_USER")
    @RequestMapping(path = "/myservice")
    public String myService(Principal principal) {
        return "Resource-Server:<br>The restricted resource artifact for user '" + principal.getName() + "'";
    }

    @RequestMapping(path = "/")
    public String info() {
        return "This is the resource server with a <a href=\"myservice\">restricted resource</a>";
    }

}
