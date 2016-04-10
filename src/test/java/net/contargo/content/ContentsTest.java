package net.contargo.content;

import net.contargo.content.Contents.Buildable;
import net.contargo.content.Contents.Builder;

import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;


/**
 * @author  Olle Törnström - toernstroem@synyx.de
 */
public class ContentsTest {

    @Test
    public void ensureContentsBuiltWithSingleSubjectNoLocale() {

        Builder b1 = Contents.withMimeType(MimeType.TEXT_SUBJECT);
        assertNotNull("Missing builder", b1);

        Buildable b2 = b1.andValue("foobar");
        assertNotNull("Missing builder", b2);
        assertNotSame("Should be different", b2, b1);

        List<Content> contents = b2.asList();

        assertNotNull("Missing results", contents);
        assertEquals("Wrong size", 1, contents.size());

        assertContentEquals(contents.get(0), MimeType.TEXT_SUBJECT, "foobar", null);
    }


    private void assertContentEquals(Content content1, MimeType type, String content, Locale locale) {

        assertEquals("Wrong mime type", type.getMimeType(), content1.getMimeType());
        assertEquals("Wrong content", content, content1.getContent());
        assertEquals("Should be empty", locale, content1.getLocale());
    }


    @Test
    public void ensureContentBuiltWithLocalizedDescription() throws Exception {

        List<? extends Content> contents = Contents.withMimeType(MimeType.TEXT_DESCRIPTION)
                .andValue("Hello", Locale.ENGLISH)
                .asList();

        assertNotNull("Missing results", contents);
        assertEquals("Wrong size", 1, contents.size());

        assertContentEquals(contents.get(0), MimeType.TEXT_DESCRIPTION, "Hello", Locale.ENGLISH);
    }


    @Test
    public void ensureTwoValuesForOneMimeType() throws Exception {

        List<Content> contents = Contents.withMimeType(MimeType.TEXT_BODY)
                .andValue("Say it")
                .andValue("Säg det", new Locale("sv"))
                .asList();

        assertNotNull("Missing results", contents);
        assertEquals("Wrong size", 2, contents.size());

        assertContentEquals(contents.get(0), MimeType.TEXT_BODY, "Say it", null);
        assertContentEquals(contents.get(1), MimeType.TEXT_BODY, "Säg det", new Locale("sv"));
    }


    @Test
    public void ensureMultipleMimeTypes() throws Exception {

        List<Content> contents = Contents.withMimeType(MimeType.TEXT_SUBJECT)
                .andValue("Introducing, the magnificent")
                .andWithMimeType(MimeType.TEXT_BODY)
                .andValue("Welcome to the first day of the rest of your life!")
                .andWithMimeType(MimeType.TEXT_DESCRIPTION)
                .andValue("Message")
                .andValue("Nachricht", Locale.GERMAN)
                .asList();

        assertNotNull("Missing results", contents);
        assertEquals("Wrong size", 4, contents.size());

        assertContentEquals(contents.get(0), MimeType.TEXT_SUBJECT, "Introducing, the magnificent", null);
        assertContentEquals(contents.get(1), MimeType.TEXT_BODY, "Welcome to the first day of the rest of your life!",
            null);
        assertContentEquals(contents.get(2), MimeType.TEXT_DESCRIPTION, "Message", null);
        assertContentEquals(contents.get(3), MimeType.TEXT_DESCRIPTION, "Nachricht", Locale.GERMAN);
    }
}
