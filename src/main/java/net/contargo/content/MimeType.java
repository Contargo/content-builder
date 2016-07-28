package net.contargo.content;

import java.util.Objects;


/**
 * The declared constant content mime-types.
 *
 * <p>Our use of a {@code mimeType} property, is how we like to classify and structure content. This class is an attempt
 * to define the currently known and supported declarations. By that I mean, since it's basically <i>possible</i> to use
 * whatever string value you want, this is merely a list of the <strong>recommended</strong> ones.</p>
 *
 * <p>Though not strictly IANA or RFC compatible, we have chosen to keep to a structure that expresses <i>some
 * compatibility, and future safety,</i> with said standards. Please note that it's currently also <strong>not</strong>
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
 * <p>The constant {@code vnd.contargo} is our vendor specifier. Which for our purposes is <strong>mandatory</strong>.
 * The {@code type}, {@code subtype} and {@code charset} values, try to follow the way that IANA/RFC based media types
 * are declared.</p>
 *
 * <p>What we describe with {@code name} should hopefully have some semantic but still be open for broad use. We always
 * try to define these types with documentation, as a hint for developers.</p>
 *
 * <p>Here's an example of what a mime type could look like: <code>image/vnd.contargo.appicon+svg</code></p>
 *
 * @author  Olle Törnström - toernstroem@synyx.de
 * @since  0.1
 */
public final class MimeType {

    /**
     * A subject should describe and give context to some content in a single line of text.
     *
     * <p>For example an email or message subject line, describing the contents.</p>
     */
    public static final MimeType TEXT_SUBJECT = MimeType.of("text/vnd.contargo.subject");

    /**
     * The description should, in a summary or short amount of text, give information about some content.
     *
     * <p>For example a clarification on some piece of information, such as it's type, structure or classification -
     * currently used to describe the domain type of a search-result.</p>
     */
    public static final MimeType TEXT_DESCRIPTION = MimeType.of("text/vnd.contargo.description");

    /**
     * Some text that represents the full content of some information.
     *
     * <p>For example a message body, from a service or machine, that has to be sent and persisted in multiple
     * languages.</p>
     */
    public static final MimeType TEXT_BODY = MimeType.of("text/vnd.contargo.body");

    private final String mimeType;

    /**
     * Constructs a new mime type with the given value.
     *
     * <p>Only available to the static factory methods.</p>
     *
     * @param  mimeType  value to create
     */
    private MimeType(String mimeType) {

        this.mimeType = mimeType;
    }

    /**
     * Returns the mime type value for this instance.
     *
     * @return  mime type value as string
     */
    String getMimeType() {

        return mimeType;
    }


    /**
     * Private static factory class, used for constant static mime type declarations.
     *
     * @param  mimeTypeValue  string for the mime type to create
     *
     * @return  a new instance of a mime type with the given value
     */
    private static MimeType of(String mimeTypeValue) {

        return new MimeType(mimeTypeValue);
    }


    @Override
    public int hashCode() {

        return Objects.hashCode(mimeType);
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof String) {
            return Objects.equals(mimeType, obj);
        }

        if (obj instanceof MimeType) {
            return Objects.equals(mimeType, ((MimeType) obj).mimeType);
        }

        return false;
    }
}
