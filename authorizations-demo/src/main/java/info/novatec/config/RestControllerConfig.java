package info.novatec.config;

import info.novatec.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful services config.
 */
@RestController
public class RestControllerConfig {

    @Autowired
    private HelloService helloService;

    @RequestMapping (path = "/")
    public String user() {
        return helloService.user ();
    }

    @RequestMapping(path = "/admin")
    public String admin() {
        return helloService.admin ();
    }

    @RequestMapping(path = "/hidden")
    public String hidden() {
        return helloService.hidden ();
    }
}
