package net.contargo.content;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * @author  Olle Törnström - toernstroem@synyx.de
 */
public class MimeTypeTest {

    @Test
    public void ensureDeclaredContentMimeTypeConstants() {

        assertEquals("text/vnd.contargo.subject", ContentMimeType.TEXT_SUBJECT);
        assertEquals("text/vnd.contargo.description", ContentMimeType.TEXT_DESCRIPTION);
        assertEquals("text/vnd.contargo.body", ContentMimeType.TEXT_BODY);
    }
}
