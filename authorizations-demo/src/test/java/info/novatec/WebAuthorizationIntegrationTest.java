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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testing the method level security.
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

	@Test
	public void verifyAdminPageGrantedForAdminRole() throws Exception {
        mvc.perform(get("/admin").with(user("admin").password("pass").roles("USER","ADMIN")))
                .andExpect ( status ().is2xxSuccessful () );
	}

    @Test
    public void verifyAdminPageDeniedForUserRole() throws Exception {
        mvc.perform(get("/admin").with(user("admin").password("pass").roles("USER")))
                .andExpect ( status ().isForbidden () );
    }

    @Test
    public void verifyLoginIsGrantedForAdminUser() throws Exception {
        mvc.perform(formLogin().user ( "admin" ).password ( "admin" ))
                .andExpect (authenticated());
    }

    @Test
    public void verifyLoginIsDeniedForWrongPassword() throws Exception {
        mvc.perform(formLogin().user ( "admin" ).password ( "dummy" ))
                .andExpect (unauthenticated());
    }

    @Test
    public void verifyLoginIsDeniedForWrongUser() throws Exception {
        mvc.perform(formLogin().user ( "hacker" ).password ( "admin" ))
                .andExpect (unauthenticated());
    }

}
