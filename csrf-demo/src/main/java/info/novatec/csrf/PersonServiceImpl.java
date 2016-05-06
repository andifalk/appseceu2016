package info.novatec.csrf;

import org.codehaus.groovy.runtime.metaclass.ConcurrentReaderHashMap;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by AFA on 06.05.2016.
 */
@Service
public class PersonServiceImpl implements PersonService {

    private Map<Integer,Person> persons = new HashMap<> ();

    @PostConstruct
    public void init() {

        Stream
            .of (
                    new Person(1,"Hans","Mustermann"),
                    new Person(2,"Sabine", "Huber"))
            .forEach (p -> persons.put ( p.getId (), p ));
    }

    @Override
    public Person create ( String firstname, String lastname ) {
        final Optional<Integer> max = persons.keySet ().stream ().max ( ( o1, o2 ) -> o1.compareTo ( o2 ) );
        if (max.isPresent ()) {
            Person person = new Person ( max.get () + 1, firstname, lastname );
            persons.put ( person.getId (), person );
            return person;
        }
        return null;
    }

    @Override
    public Person findById ( int id ) {
        return persons.get ( id );
    }

    @Override
    public Collection<Person> findAll () {
        return persons.values ();
    }
}
