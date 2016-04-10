package net.contargo.content;

import org.junit.Test;

import java.util.Locale;

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
}
