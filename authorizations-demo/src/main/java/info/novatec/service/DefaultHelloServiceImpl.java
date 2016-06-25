package info.novatec.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Hello Service impl.
 */
@Service
public class DefaultHelloServiceImpl implements HelloService {

    @Secured( "ROLE_USER" )
    @Override
    public String user() {
        return "User access";
    }

    @Secured ( "ROLE_ADMIN" )
    @Override
    public String admin() {
        return "Admin access";
    }

    /*
     * This is only secured by method layer security
     */
    //@Secured ( "ROLE_ADMIN" )
    @Override
    public String hidden() {
        return "Hidden Admin access";
    }
}
