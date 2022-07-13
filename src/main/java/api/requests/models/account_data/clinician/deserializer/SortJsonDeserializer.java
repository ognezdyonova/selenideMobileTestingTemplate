package api.requests.models.account_data.clinician.deserializer;

import api.requests.models.account_data.clinician.filters.Sort;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class SortJsonDeserializer extends JsonDeserializer<Sort> {
    @Override
    public Sort deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        if (!node.isArray()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(String.valueOf(node), new TypeReference<Sort>() {
            });
        }
        return null;
    }
}
