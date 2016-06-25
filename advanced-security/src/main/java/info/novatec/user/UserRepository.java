package info.novatec.user;

import org.springframework.data.repository.CrudRepository;

/**
 * User data access.
 */
public interface UserRepository extends CrudRepository<User,Long> {

    /**
     * Find a user by its user name
     * @param username user name
     * @return user
     */
    User findOnyByUsername(String username);
}
