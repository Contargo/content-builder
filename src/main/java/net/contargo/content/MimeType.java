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
${type}/vnd.contargo.${name};${params}
${type}/vnd.contargo.${name}+${subtype}
${type}/vnd.contargo.${name}+${subtype};${params}
   </code></pre>
 *
 * <p>The constant {@code vnd.contargo} is our vendor specifier. Which for our purposes is <strong>mandatory</strong>.
 * The {@code type}, {@code subtype} and {@code params} values, try to follow the way that IANA/RFC based media types
 * are declared.</p>
 *
 * <p>What we describe with {@code name} should hopefully have some semantic but still be open for broad use. We always
 * try to define these types with documentation, as a hint for developers.</p>
 *
 * <p>Here's an example of what a mime type could look like: <code>text/vnd.contargo.appicon+uri;class=small</code></p>
 *
 * <p>In this example a mime-type <em>parameter</em> is used to mark the content with a <code>class</code>, this is not
 * a specific parameter but an option for the authoring code, as means to extend the specification of the provided
 * content, to the user.</p>
 *
 * @author  Olle Törnström - toernstroem@synyx.de
 * @since  0.1
 */
public final class MimeType {

    /**
     * @see  #TEXT_SUBJECT
     */
    public static final String TEXT_SUBJECT_VAL = "text/vnd.contargo.subject";

    /**
     * @see  #TEXT_DESCRIPTION
     */
    public static final String TEXT_DESCRIPTION_VAL = "text/vnd.contargo.description";

    /**
     * @see  #TEXT_BODY
     */
    public static final String TEXT_BODY_VAL = "text/vnd.contargo.body";

    /**
     * @see  #TEXT_APPICON
     */
    public static final String TEXT_APPICON_VAL = "text/vnd.contargo.appicon";

    /**
     * @see  #IMAGE_APPICON
     */
    public static final String IMAGE_APPICON_VAL = "image/vnd.contargo.appicon";

    /**
     * A subject should describe and give context to some content in a single line of text: <code>
     * {@value #TEXT_SUBJECT_VAL}</code>.
     *
     * <p>For example an email or message subject line, describing the contents.</p>
     */
    public static final MimeType TEXT_SUBJECT = MimeType.of(TEXT_SUBJECT_VAL);

    /**
     * The description should, in a summary or short amount of text, give information about some content: <code>
     * {@value #TEXT_DESCRIPTION_VAL}</code>.
     *
     * <p>For example a clarification on some piece of information, such as it's type, structure or classification -
     * currently used to describe the domain type of a search-result.</p>
     */
    public static final MimeType TEXT_DESCRIPTION = MimeType.of(TEXT_DESCRIPTION_VAL);

    /**
     * Some text that represents the full content of some information: <code> {@value #TEXT_BODY_VAL}</code>
     *
     * <p>For example a message body, from a service or machine, that has to be sent and persisted in multiple
     * languages.</p>
     */
    public static final MimeType TEXT_BODY = MimeType.of(TEXT_BODY_VAL);

    /**
     * Describes an application icon image resource reference, such as a path or a URI: <code>{@value #TEXT_APPICON_VAL}</code>.
     *
     * <p>Typically provided for the rendering of a size-independent (scalable) image or background. More specifically,
     * the URI to an application's SVG icon resource.</p>
     *
     * <p>NOTE: It is intended for the content author to optionally extend the content with a classification, which may
     * allow for more suitable use by the client. Such classifications are preferably to apply with a mime-type
     * parameter, for example: {@code ;class=small}</p>
     *
     * @since  0.2
     */
    public static final MimeType TEXT_APPICON = MimeType.of(TEXT_APPICON_VAL);

    /**
     * Describes an application binary image icon <code>{@value #IMAGE_APPICON_VAL}</code>.
     *
     * @since  0.2
     */
    public static final MimeType IMAGE_APPICON = MimeType.of(IMAGE_APPICON_VAL);

    private final String mimeType;

    /**
     * Constructs a new mime type with the given value.
     *
     * <p>Absolutely <strong>no checks or validations made</strong>. The provided mime type string will be used
     * as-is.</p>
     *
     * @param  mimeType  value to create
     */
    public MimeType(String mimeType) {

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
