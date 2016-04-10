package net.contargo.content;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


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
}
