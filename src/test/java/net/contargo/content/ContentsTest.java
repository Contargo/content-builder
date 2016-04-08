package net.contargo.content;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author  Olle Törnström - toernstroem@synyx.de
 */
public class ContentsTest {

    @Test
    public void ensureBuilderBuildsEmptyContentsCollection() {

        List<Content> results = Contents.build();
        assertNotNull("Missing results", results);
        assertEquals("Wrong size", 0, results.size());
    }
}
