package info.novatec;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Verifying security at UI level (and method level).
 *
 * <ul>
 *  <li>Verifies form login</li>
 *  <li>Verifies logout</li>
 *  <li>Verifies authorizations</li>
 * </ul>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AuthorizationsDemoApplication.class)
@WebIntegrationTest(randomPort = true)
public class WebAuthorizationIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    /**
     * Verify user with ROLE_ADMIN can access '/admin' page.
     * @throws Exception
     */
	@Test
	public void verifyAdminPageGrantedForAdminRole() throws Exception {
        mvc.perform(get("/admin").with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect ( status ().is2xxSuccessful () );
	}

    /**
     * Verify user with ROLE_USER gets forbidden for '/admin' page.
     * @throws Exception
     */
    @Test
    public void verifyAdminPageDeniedForUserRole() throws Exception {
        mvc.perform(get("/admin").with(user("admin").password("pass").roles("USER")))
                .andExpect ( status ().isForbidden () );
    }

    /**
     * Verify user with ROLE_ADMIN can access '/hidden' page.
     * @throws Exception
     */
    @Test
    public void verifyHiddenPageGrantedForAdminRole() throws Exception {
        mvc.perform(get("/hidden").with(user("admin").password("admin").roles("USER", "ADMIN")))
                .andExpect ( status ().isOk() );
    }

    /**
     * Verify user with ROLE_USER gets forbidden for '/hidden' page.
     * @throws Exception
     */
    @Test
    public void verifyHiddenPageDeniedForUserRole() throws Exception {
        mvc.perform(get("/hidden").with(user("admin").password("pass").roles("USER")))
                .andExpect ( status ().isForbidden () );
    }

    /**
     * Verify user 'user' can perform login.
     * @throws Exception
     */
    @Test
    public void verifyLoginIsGrantedForUser() throws Exception {
        mvc.perform(formLogin().user ( "user" ).password ( "user" ))
                .andExpect (authenticated().withRoles("USER"));
    }

    /**
     * Verify user 'admin' can perform login.
     * @throws Exception
     */
    @Test
    public void verifyLoginIsGrantedForAdminUser() throws Exception {
        mvc.perform(formLogin().user ( "admin" ).password ( "admin" ))
                .andExpect (authenticated().withRoles("USER", "ADMIN"));
    }

    /**
     * Verify user 'admin' with wrong password cannot log in.
     * @throws Exception
     */
    @Test
    public void verifyLoginIsDeniedForWrongPassword() throws Exception {
        mvc.perform(formLogin().user ( "admin" ).password ( "dummy" ))
                .andExpect (unauthenticated());
    }

    /**
     * Verify an unknown user cannot log in.
     * @throws Exception
     */
    @Test
    public void verifyLoginIsDeniedForWrongUser() throws Exception {
        mvc.perform(formLogin().user ( "hacker" ).password ( "admin" ))
                .andExpect (unauthenticated());
    }

    /**
     * Verify logout.
     * @throws Exception
     */
    @Test
    public void verifyLogout() throws Exception {
        mvc.perform(logout())
                .andExpect (unauthenticated());
    }

}
