package net.contargo.content;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author  Olle Törnström - toernstroem@synyx.de
 */
public class ContentTest {

    @Test
    public void ensureHasPrettyToString() throws Exception {

        Content c1 = new Content("type", "value");
        String string1 = c1.toString();
        assertTrue("Missing information: '" + string1 + "'", string1.contains("mimeType=type"));
        assertTrue("Missing information: '" + string1 + "'", string1.contains("content=value"));
        assertFalse("Must not have information + '" + string1 + "'", string1.contains("locale="));

        Content c2 = new Content("type", "value", Locale.ENGLISH);
        String string2 = c2.toString();
        assertTrue("Missing information: '" + string2 + "'", string2.contains("locale=en"));
    }


    @Test
    public void ensureReadsProperContentFromJSON() throws Exception {

        String json =
            "{\"mimeType\": \"text/vnd.contargo.appicon\", \"content\": \"some-app-icon\", \"locale\": \"sv\"}";

        Content content = new ObjectMapper().readValue(json.getBytes(), Content.class);

        assertEquals(MimeType.TEXT_APPICON_VAL, content.getMimeType());
        assertEquals("some-app-icon", content.getContent().toString());
        assertEquals(new Locale("sv").getLanguage(), content.getLocale().getLanguage());
    }


    @Test
    public void ensureReadsPropertByteContentFromJSON() throws Exception {

        byte[] bytes = new byte[] { 1, 2, 3 };

        String json = Contents.withMimeType(MimeType.IMAGE_APPICON)
                .andValue(bytes)
                .asJSON()
                .replace("[", "")
                .replace("]", "");

        Content content = new ObjectMapper().readValue(json.getBytes(), Content.class);

        assertEquals(MimeType.IMAGE_APPICON_VAL, content.getMimeType());
        assertArrayEquals(bytes, (byte[]) content.getContent());
    }
}
