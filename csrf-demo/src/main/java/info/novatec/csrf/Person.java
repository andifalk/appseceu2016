package info.novatec.csrf;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by AFA on 06.05.2016.
 */
public class Person implements Serializable {

    private final int id;
    private String firstname;
    private String lastname;

    public Person () {
        this.id = 0;
    }

    public Person ( int id, String firstname, String lastname ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname () {
        return firstname;
    }

    public void setFirstname ( String firstname ) {
        this.firstname = firstname;
    }

    public String getLastname () {
        return lastname;
    }

    public void setLastname ( String lastname ) {
        this.lastname = lastname;
    }

    public int getId () {
        return id;
    }

    @Override
    public String toString () {
        return new ToStringBuilder ( this )
                .append ( "id", id )
                .append ( "firstname", firstname )
                .append ( "lastname", lastname )
                .toString ();
    }
}
