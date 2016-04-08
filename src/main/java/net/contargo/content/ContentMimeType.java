package net.contargo.content;

/**
 * Provides the declared constant content mime-types.
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
