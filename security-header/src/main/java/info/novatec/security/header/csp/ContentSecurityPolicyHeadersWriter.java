package info.novatec.security.header.csp;

import org.springframework.security.web.header.HeaderWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

/**
 * Headers writer for content security policy according to https://www.w3.org/TR/CSP.
 */
@SuppressWarnings ( "unused" )
public final class ContentSecurityPolicyHeadersWriter implements HeaderWriter {

    private StringBuilder entries;
    private boolean reportOnly;

    private ContentSecurityPolicyHeadersWriter () {
        entries = new StringBuilder ();
    }

    public static ContentSecurityPolicyHeadersWriter create() {
        return new ContentSecurityPolicyHeadersWriter ();
    }

    public ContentSecurityPolicyHeadersWriter defaultSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.DEFAULT_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter childSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.CHILD_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter connectSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.CONNECT_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter fontSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.FONT_SRC, value );
        return this;
    }
    public ContentSecurityPolicyHeadersWriter formAction(String value) {
        addEntry ( ContentSecurityPolicyDirective.FORM_ACTION, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter frameAncestors(String value) {
        addEntry ( ContentSecurityPolicyDirective.FRAME_ANCESTORS, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter frameSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.FRAME_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter imageSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.IMG_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter mediaSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.MEDIA_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter objectSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.OBJECT_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter pluginTypes(String value) {
        addEntry ( ContentSecurityPolicyDirective.PLUGIN_TYPES, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter sandbox(String value) {
        addEntry ( ContentSecurityPolicyDirective.SANDBOX, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter scriptSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.SCRIPT_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter styleSource(String value) {
        addEntry ( ContentSecurityPolicyDirective.STYLE_SRC, value );
        return this;
    }

    public ContentSecurityPolicyHeadersWriter reportOnly() {
        this.reportOnly = true;
        return this;
    }

    public ContentSecurityPolicyHeadersWriter reportUri( URI uri) {
        addEntry ( ContentSecurityPolicyDirective.REPORT_URI, uri.toASCIIString () );
        return this;
    }

    @Override
    public void writeHeaders ( HttpServletRequest request, HttpServletResponse response ) {
        if (reportOnly) {
            response.addHeader ( "Content-Security-Policy-Report-Only", entries.toString () );
        } else {
            response.addHeader ( "Content-Security-Policy", entries.toString () );
        }
    }

    private void addEntry ( ContentSecurityPolicyDirective directive, String value ) {
        entries.append ( directive.getDirective () ).append(" ").append ( value ).append ( "; " );
    }

}
