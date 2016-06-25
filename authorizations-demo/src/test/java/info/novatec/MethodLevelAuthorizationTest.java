package info.novatec;

import info.novatec.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Verifies method level security.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthorizationsDemoApplication.class)
public class MethodLevelAuthorizationTest {

	@Autowired
	private HelloService cut;

    /**
     * Verify user with role 'ADMIN' can access method 'admin()'.
     */
	@WithMockUser(roles = "ADMIN")
	@Test
	public void verifyAdminMethodGrantedForAdminRole() {
		assertThat (cut.admin (), is ("Admin access"));
	}

    /**
     * Verify user with role 'USER' is denied access to method 'admin()'.
     */
	@WithMockUser(roles = "USER")
	@Test(expected = AccessDeniedException.class)
	public void verifyAdminMethodDeniedForUserRole() {
		cut.admin ();
	}

    /**
     * Verify user with role 'ADMIN' can access method 'hidden()'.
     */
    @WithMockUser(roles = "ADMIN")
    @Test
    public void verifyHiddenMethodGrantedForAdminRole() {
        assertThat (cut.hidden (), is ("Hidden Admin access"));
    }

    /**
     * Verify user with role 'USER' is denied access method 'hidden()'.
     */
    @WithMockUser(roles = "USER")
    @Test(expected = AccessDeniedException.class)
    public void verifyHiddenMethodDeniedForUserRole() {
        cut.hidden ();
    }

    /**
     * Verify user with role 'USER' can access method 'user()'.
     */
    @WithMockUser(roles = "USER")
    @Test
    public void verifyUserMethodGrantedForUserRole() {
        assertThat (cut.user (), is ("User access"));
    }

    /**
     * Verify unauthenticated user is denied access to method 'user()'.
     */
    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void verifyUserMethodDeniedForUnauthenticated() {
        cut.user ();
    }

}
