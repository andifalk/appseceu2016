package info.novatec.security.header.config;

import java.util.Collections;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

/**
 * Workaround to set httpOnly flag to false for cookies.
 */
@SuppressWarnings ( "unused" )
@Component
public class TomcatCustomizer implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize ( ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer ) {
        if (configurableEmbeddedServletContainer instanceof TomcatEmbeddedServletContainerFactory) {
            ((TomcatEmbeddedServletContainerFactory) configurableEmbeddedServletContainer).setTomcatContextCustomizers (
                    Collections.singletonList ( (TomcatContextCustomizer) context -> context.setUseHttpOnly ( false ) ) );
        }
    }
}
