package info.novatec.service;

import org.springframework.security.access.annotation.Secured;

/**
 * Created by afa on 04.03.16.
 */
public interface HelloService {

    @Secured ( "ROLE_USER" )
    String user();

    @Secured ( "ROLE_ADMIN" )
    String admin();

    @Secured ( "ROLE_ADMIN" )
    String hidden();

}
