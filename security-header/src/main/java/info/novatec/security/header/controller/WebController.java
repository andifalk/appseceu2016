package info.novatec.security.header.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for hacking jsp pages.
 */
@SuppressWarnings ( "unused" )
@Controller
public class WebController {

    @RequestMapping("/xss")
    public String welcome( @RequestParam ("msg") String msg, Map<String, Object> model) {
        model.put("message", msg);
        return "reflected";
    }

    @RequestMapping("/index")
    public String index(Map<String, Object> model) {
        return "index";
    }
}
