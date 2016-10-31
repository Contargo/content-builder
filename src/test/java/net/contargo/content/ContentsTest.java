package net.contargo.content;

import net.contargo.content.Contents.Buildable;
import net.contargo.content.Contents.Builder;

import org.junit.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;


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


    @Test
    public void ensureBuildsAsProperMap() throws Exception {

        List<Map<String, Object>> contents = Contents.withMimeType(MimeType.TEXT_BODY)
                .andValue("Say it")
                .andValue("Säg det", new Locale("sv"))
                .asMap();

        assertNotNull("Missing results", contents);
        assertEquals("Wrong size", 2, contents.size());

        assertContentEquals(contents.get(0), MimeType.TEXT_BODY, "Say it", null);
        assertContentEquals(contents.get(1), MimeType.TEXT_BODY, "Säg det", new Locale("sv"));
    }


    private void assertContentEquals(Map<String, Object> entry, MimeType type, String content, Locale locale) {

        assertTrue("Missing required key: mimeType", entry.containsKey("mimeType"));
        assertTrue("Missing required key: content", entry.containsKey("content"));

        assertEquals("Wrong mime type", type.getMimeType(), entry.get("mimeType"));
        assertEquals("Wrong content", content, entry.get("content"));
        assertEquals("Wrong locale", Optional.ofNullable(locale).map(Locale::toString).orElse(null),
            entry.get("locale"));
    }


    @Test
    public void ensureReturnsJsonString() throws Exception {

        String json = Contents.withMimeType(MimeType.TEXT_BODY)
                .andValue("Say it")
                .andValue("Säg det", new Locale("sv"))
                .asJSON();

        JSONAssert.assertEquals("["
            + "{mimeType: 'text/vnd.contargo.body', content: 'Say it'}, "
            + "{mimeType: 'text/vnd.contargo.body', content: 'Säg det', locale: 'sv'}]", json, true);
    }


    @Test
    public void ensureRetrievesContentByMimeType() throws Exception {

        List<Content> contents = Contents.withMimeType(MimeType.TEXT_BODY)
                .andValue("Say it")
                .andValue("Säg det", new Locale("sv"))
                .asList();

        String value = new Contents(contents).forMimeType(MimeType.TEXT_BODY);

        assertEquals("Say it", value);
    }


    @Test
    public void ensureMatchesVariants() throws Exception {

        List<Content> contents = Contents.withMimeType(MimeType.TEXT_BODY.withParams("foo"))
                .andValue("variant")
                .andWithMimeType(MimeType.TEXT_BODY)
                .andValue("default")
                .asList();

        String v1 = new Contents(contents).forMimeType(MimeType.TEXT_BODY);
        String v2 = new Contents(contents).forMimeType(MimeType.TEXT_BODY.withParams("foo"));

        assertEquals("default", v1);
        assertEquals("variant", v2);
    }


    @Test
    public void ensureRetreivesContentByMimeTypeAndLocale() throws Exception {

        List<Content> contents = Contents.withMimeType(MimeType.TEXT_BODY)
                .andValue("Say it")
                .andValue("Säg det", new Locale("sv"))
                .asList();

        String value = new Contents(contents).forMimeTypeAndLocale(MimeType.TEXT_BODY, new Locale("sv"));

        assertEquals("Säg det", value);
    }


    @Test
    public void ensureCanCreateAndRetriveBinaryImageData() throws Exception {

        byte[] image = new byte[] { 1, 2, 3 };
        List<Content> contents = Contents.withMimeType(MimeType.IMAGE_APPICON).andValue(image).asList();

        byte[] value = new Contents(contents).forMimeType(MimeType.IMAGE_APPICON);
        assertArrayEquals(image, value);
    }


    @Test
    public void ensureIgnoresEmptyContentEntries() throws Exception {

        List<Content> contents = Contents.withMimeType(MimeType.TEXT_APPICON)
                .andValue("    ")
                .andWithMimeType(MimeType.TEXT_SUBJECT)
                .andValue("")
                .andWithMimeType(MimeType.TEXT_BODY)
                .andValue((String) null)
                .andWithMimeType(MimeType.TEXT_DESCRIPTION)
                .andValue("foo")
                .asList();

        assertNotNull("Missing results", contents);
        assertEquals("Wrong size", 1, contents.size());
        assertContentEquals(contents.get(0), MimeType.TEXT_DESCRIPTION, "foo", null);
    }
}
