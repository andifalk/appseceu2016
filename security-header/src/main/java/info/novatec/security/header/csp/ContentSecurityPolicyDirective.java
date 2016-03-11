package info.novatec.security.header.csp;

/**
 * Content security policy directives.
 */
enum ContentSecurityPolicyDirective {

    DEFAULT_SRC("default-src"),
    CHILD_SRC("child-src"),
    CONNECT_SRC("connect-src"),
    FONT_SRC("font-src"),
    FORM_ACTION("form-action"),
    FRAME_ANCESTORS("frame-ancestors"),
    FRAME_SRC("frame-src"),
    IMG_SRC("img-src"),
    MEDIA_SRC("media-src"),
    OBJECT_SRC("object-src"),
    PLUGIN_TYPES("plugin-types"),
    REPORT_URI("report-uri"),
    SANDBOX("sandbox"),
    SCRIPT_SRC("script-src"),
    STYLE_SRC("style-src");

    private String directive;

    ContentSecurityPolicyDirective ( String directive ) {
        this.directive = directive;
    }

    String getDirective () {
        return directive;
    }
}
