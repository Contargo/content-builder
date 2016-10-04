package net.contargo.content;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

import java.util.Locale;


/**
 * Custom deserializer for content entries.
 *
 * @author  Olle Törnström - toernstroem@synyx.de
 * @since  0.2
 */
public final class ContentDeserializer extends StdDeserializer<Content> {

    private static final String ObjectMapper = null;

    public ContentDeserializer() {

        super(Content.class);
    }

    @Override
    public Content deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();

        TreeNode node = p.getCodec().readTree(p);

        String mimeType = ((TextNode) node.get("mimeType")).textValue();

        final Object content;

        if (mimeType.startsWith("text")) {
            content = ((TextNode) node.get("content")).textValue();
        } else {
            content = ((TextNode) node.get("content")).getBinaryValue(Base64Variants.MIME_NO_LINEFEEDS);
        }

        if (node.path("locale").isValueNode()) {
            return new Content(mimeType, content, node.get("locale").traverse(mapper).readValueAs(Locale.class));
        }

        return new Content(mimeType, content);
    }
}
