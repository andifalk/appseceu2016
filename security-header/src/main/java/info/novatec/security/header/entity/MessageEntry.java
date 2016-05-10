package info.novatec.security.header.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * JPA entity to store message values.
 */
@Entity
public class MessageEntry extends AbstractPersistable<Long> {

    @NotNull
    @Size(min = 1, max = 200)
    private String value;

    @NotNull
    @Size(min = 1, max = 20)
    private String identifier;

    public MessageEntry () {
    }

    public MessageEntry ( String identifier, String value ) {
        this.value = value;
        this.identifier = identifier;
    }

    public String getValue () {
        return value;
    }

    public void setValue ( String value ) {
        this.value = value;
    }

    public String getIdentifier () {
        return identifier;
    }

    public void setIdentifier ( String identifier ) {
        this.identifier = identifier;
    }

    @Override
    public String toString () {
        return new ToStringBuilder ( this )
                .append ( "value", value )
                .append ( "identifier", identifier )
                .toString ();
    }
}
