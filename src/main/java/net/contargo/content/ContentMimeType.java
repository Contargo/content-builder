package net.contargo.content;

/**
 * Provides the declared constant content mime-types.
 *
 * <p>Our use of a {@code mimeType} property, is how we like to classify and structure content. This class is an attempt
 * to define the currently known and supported declarations.</p>
 *
 * <p>Though not strictly IANA or RFC compatible, we have chosen to keep to a structure that expresses <i>some
 * compatibility</i> (and future safety) with said standards. Please note that it's currently also <strong>not</strong>
 * the intent to publish these type definitions, outside of the COLA system landscape.</p>
 *
 * <p>Our mime type definitions are defined using the following pattern, with optional variations:</p>
 *
 * <pre><code>
${type}/vnd.contargo.${name}
${type}/vnd.contargo.${name};charset=${charset}
${type}/vnd.contargo.${name}+${subtype}
${type}/vnd.contargo.${name}+${subtype};charset=${charset}
   </code></pre>
 *
 * <p>The constant {@code vnd.contargo} is our vendor specifier.</p>
 *
 * @author  Olle Törnström - toernstroem@synyx.de
 * @since  0.1
 */
public final class ContentMimeType {

    private static final String _P = "text/vnd.contargo.";

    /**
     * A subject should describe and give context to some content in a single line of text.
     */
    public static final String TEXT_SUBJECT = _P + "subject";

    /**
     * The description should, in a summary or short amount of text, give information about some content.
     */
    public static final String TEXT_DESCRIPTION = _P + "description";

    /**
     * Some text that represents the full content of some information.
     */
    public static final String TEXT_BODY = _P + "body";
}
