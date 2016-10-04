package net.contargo.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.text.MessageFormat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;


/**
 * A COLA Common Content Format entry.
 *
 * <p>This class represents the unit of a content entry, in the COLA Common Content Format. It will contain the
 * mime-type description and content data, with an optional language or locale, for localization.</p>
 *
 * @author  Olle Törnström - toernstroem@synyx.de
 * @since  0.1
 */
@JsonInclude(Include.NON_ABSENT)
public class Content {

    private final String mimeType;
    private final Object content;
    private final Optional<Locale> locale;

    /**
     * Package protected constructor, for content entry instances without a locale.
     *
     * @param  mimeType  of the content entry to create
     * @param  content  of the entry
     */
    Content(String mimeType, Object content) {

        this(mimeType, content, Optional.empty());
    }


    /**
     * Package protected constructor, for content entry instances with byte-array content but no locale.
     *
     * @param  mimeType  of the content entry to create
     * @param  content  of the entry
     */
    Content(String mimeType, byte[] content) {

        this(mimeType, content, Optional.empty());
    }


    /**
     * Package protected constructor, for content entry instances with a language locale.
     *
     * @param  mimeType  of the content entry to create
     * @param  content  of the entry
     * @param  locale  specifying the localized language of the entry
     */
    Content(String mimeType, Object content, Locale locale) {

        this(mimeType, content, Optional.of(locale));
    }


    /**
     * Package protected constructor, for content entry instances with byte-array content and a specific language
     * locale.
     *
     * @param  mimeType  of the content entry to create
     * @param  content  of the entry
     * @param  locale  specifying the localized language of the entry
     */
    Content(String mimeType, byte[] content, Locale locale) {

        this(mimeType, content, Optional.of(locale));
    }


    /**
     * Private constructor for content entries.
     *
     * @param  mimeType  of the entry
     * @param  content  of the entry
     * @param  locale  of the entry
     */
    private Content(String mimeType, Object content, Optional<Locale> locale) {

        this.mimeType = mimeType;
        this.content = content;
        this.locale = locale;
    }

    /**
     * Returns the mime type of this content entry.
     *
     * @return  mime type string
     */
    public String getMimeType() {

        return this.mimeType;
    }


    /**
     * Returns the content data of this entry.
     *
     * @return  content data object
     */
    public Object getContent() {

        return content;
    }


    /**
     * Returns the locale of this content entry.
     *
     * @return  the entry locale or {@code null} if it does not exist
     */
    public Locale getLocale() {

        return locale.orElse(null);
    }


    @Override
    public String toString() {

        if (locale.isPresent()) {
            return MessageFormat.format("Content [mimeType={0}, content={1}, locale={2}]", mimeType, content,
                    locale.get());
        }

        return MessageFormat.format("Content [mimeType={0}, content={1}]", mimeType, content);
    }


    static Map<String, Object> asMap(Content content) {

        Map<String, Object> map = new HashMap<>(3);

        map.put("mimeType", content.getMimeType());
        map.put("content", content.getContent());

        Optional.ofNullable(content.getLocale()).ifPresent(l -> map.put("locale", l.toString()));

        return map;
    }


    boolean forMimeType(MimeType mimeType) {

        return this.mimeType.equals(mimeType.getMimeType());
    }


    boolean forMimeTypeAndLocale(MimeType mimeType, Locale locale) {

        String lang = this.locale.map(Locale::getLanguage).orElse(null);

        return locale.getLanguage().equals(lang) && this.mimeType.equals(mimeType.getMimeType());
    }
}
