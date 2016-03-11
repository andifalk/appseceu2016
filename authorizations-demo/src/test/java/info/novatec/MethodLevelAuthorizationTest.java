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
 * Testing the method level security.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthorizationsDemoApplication.class)
public class MethodLevelAuthorizationTest {

	@Autowired
	private HelloService cut;

	@WithMockUser(roles = "ADMIN")
	@Test
	public void verifyAdminPageGrantedForAdminRole() {
		assertThat (cut.admin (), is ("Admin access"));
	}

	@WithMockUser(roles = "USER")
	@Test(expected = AccessDeniedException.class)
	public void verifyAdminPageDeniedForUserRole() {
		cut.admin ();
	}

    @WithMockUser(roles = "ADMIN")
    @Test
    public void verifyHiddenPageGrantedForAdminRole() {
        assertThat (cut.hidden (), is ("Hidden Admin access"));
    }

    @WithMockUser(roles = "USER")
    @Test(expected = AccessDeniedException.class)
    public void verifyHiddenPageDeniedForUserRole() {
        cut.hidden ();
    }

    @WithMockUser(roles = "USER")
    @Test
    public void verifyUserPageGrantedForUserRole() {
        assertThat (cut.user (), is ("User access"));
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void verifyUserPageDeniedForUnauthenticated() {
        cut.user ();
    }

}
