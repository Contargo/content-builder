package net.contargo.content;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * @author  Olle Törnström - toernstroem@synyx.de
 */
public class MimeTypeTest {

    @Test
    public void ensureDeclaredContentMimeTypeConstants() {

        assertEquals("text/vnd.contargo.subject", MimeType.TEXT_SUBJECT.getMimeType());
        assertEquals("text/vnd.contargo.description", MimeType.TEXT_DESCRIPTION.getMimeType());
        assertEquals("text/vnd.contargo.body", MimeType.TEXT_BODY.getMimeType());
    }


    @Test
    public void ensureEqualsForInstancesAreCorrect() throws Exception {

        assertTrue(MimeType.TEXT_SUBJECT.equals(MimeType.TEXT_SUBJECT));
        assertTrue(MimeType.TEXT_DESCRIPTION.equals(MimeType.TEXT_DESCRIPTION));

        assertTrue(MimeType.TEXT_BODY.equals(MimeType.TEXT_BODY));
        assertFalse(MimeType.TEXT_BODY.equals(MimeType.TEXT_DESCRIPTION));
        assertFalse(MimeType.TEXT_BODY.equals(MimeType.TEXT_SUBJECT));
    }


    @Test
    public void ensureEqualsForStringComparisonIsAvailable() throws Exception {

        assertTrue(MimeType.TEXT_SUBJECT.equals("text/vnd.contargo.subject"));
        assertFalse(MimeType.TEXT_SUBJECT.equals("text/vnd.contargo.description"));

        assertTrue(MimeType.TEXT_DESCRIPTION.equals("text/vnd.contargo.description"));
        assertTrue(MimeType.TEXT_BODY.equals("text/vnd.contargo.body"));
    }


    @Test
    public void ensureCanCreateMimeTypeVariantsWithParams() throws Exception {

        assertEquals("text/vnd.contargo.appicon;foobar", MimeType.TEXT_APPICON.withParams("foobar").getMimeType());
    }
}
