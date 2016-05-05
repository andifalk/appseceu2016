package info.novatec.appsensor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by afa on 15.04.16.
 */
@RestController
public class DemoController {

    @RequestMapping(path = "/")
    public String index() {
        return "hello world";
    }
}
