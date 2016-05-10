package info.novatec.security.header.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.novatec.security.header.entity.MessageEntry;

/**
 * JPA repository for {@link MessageEntry}.
 */
public interface MessageEntryRepository extends JpaRepository<MessageEntry,Long> {

    MessageEntry findOneByIdentifier(String identifier);
}
