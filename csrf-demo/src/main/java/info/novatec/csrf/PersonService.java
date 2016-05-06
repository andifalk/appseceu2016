package info.novatec.csrf;

import java.util.Collection;

/**
 * Created by AFA on 06.05.2016.
 */
public interface PersonService {

    Person create(String firstname, String lastname);

    Person findById ( int id );

    Collection<Person> findAll ();
}
