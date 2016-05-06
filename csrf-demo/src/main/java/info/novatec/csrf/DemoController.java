package info.novatec.csrf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * Created by AFA on 06.05.2016.
 */
@Controller
public class DemoController {

    private final PersonService personService;

    @Autowired
    public DemoController ( PersonService personService ) {
        this.personService = personService;
    }

    @ModelAttribute
    public Person person() {
        return new Person ();
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping( path = "/create", method = RequestMethod.POST)
    public String createPersonPost( @RequestBody MultiValueMap<String,String> values, Model model ) {
        model.addAttribute ( "message", "Hello AppSec" );

        final String firstname = values.toSingleValueMap ().get ( "firstname" );
        final String lastname = values.toSingleValueMap ().get ( "lastname" );

        final Person person = personService.create ( firstname, lastname );
        if (person != null) {
            model.addAttribute ( "id", person.getId ());
        }

        return "postresult";
    }

    @RequestMapping( path = "/create", method = RequestMethod.GET)
    public String createPersonGet(
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName, Model model ) {

        final Person person = personService.create ( firstName, lastName );
        if (person != null) {
            model.addAttribute ( "id", person.getId ());
        }

        return "postresult";
    }


    @RequestMapping(path = "/createform", method = RequestMethod.GET)
    public String postForm() {
        return "post";
    }



    @RequestMapping( path = "/person")
    public String getPerson( @RequestParam("id") int id, Model model) {
        final Person person = personService.findById ( id );
        if (person != null) {
            model.addAttribute ( "firstname", person.getFirstname () );
            model.addAttribute ( "lastname", person.getLastname () );
        }
        return "get";
    }

    @RequestMapping( path = "/persons")
    public String getPersonen(Model model) {
        final Collection<Person> persons = personService.findAll ();
        if (persons != null) {
            model.addAttribute ( "allPersons", persons );
        }
        return "getall";
    }

}
