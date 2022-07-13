package api.requests.models.account_data.clinician.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TermJsonDeserializer extends JsonDeserializer<List<Object>> {
    @Override
    public List<Object> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        if (node.asText().startsWith("\"") && node.asText().endsWith("\"")) {
            String substring = node.asText().replace("\"", "");
            if (substring.length() > 0) {
                if (StringUtils.isNumericSpace(substring)) {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(substring);
                    return list;
                } else {
                    ArrayList<Object> list = new ArrayList<>();
                    list.add(0);
                    return list;
                }
            }
        }

        if (!"".equals(node.textValue()) && node.isArray()) {

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(String.valueOf(node), new TypeReference<List<Object>>() {
            });
        }
        return new ArrayList<>();
    }
}
