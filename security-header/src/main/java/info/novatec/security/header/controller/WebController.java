package info.novatec.security.header.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import info.novatec.security.header.entity.MessageEntry;
import info.novatec.security.header.repository.MessageEntryRepository;

/**
 * Controller for hacking jsp pages.
 */
@SuppressWarnings ( "unused" )
@Controller
public class WebController {

    private MessageEntryRepository messageEntryRepository;

    @RequestMapping("/xss")
    public String reflectedXss( @RequestParam ("msg") String msg, Map<String, Object> model) {
        model.put("message", msg);
        return "reflected";
    }

    @RequestMapping("/safe-xss")
    public String safeReflectedXss( @RequestParam ("msg") String msg, Map<String, Object> model) {
        model.put("message", msg);
        return "safe_reflected";
    }

    @RequestMapping("/persistent")
    public String persistentXss( Map<String, Object> model) {
        MessageEntry messageEntry = messageEntryRepository.findOneByIdentifier ( "hello" );
        if (messageEntry != null) {
            model.put ( "message", messageEntry.getValue ());
        } else {
            model.put ( "message", "Not found" );
        }
        return "persistent";
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }

    @Autowired
    public void setMessageEntryRepository ( MessageEntryRepository messageEntryRepository ) {
        this.messageEntryRepository = messageEntryRepository;
    }
}
