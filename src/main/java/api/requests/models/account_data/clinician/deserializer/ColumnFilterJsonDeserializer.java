package api.requests.models.account_data.clinician.deserializer;

import api.requests.models.account_data.clinician.filters.FilterInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ColumnFilterJsonDeserializer extends JsonDeserializer<List<FilterInfo>> {
    @Override
    public List<FilterInfo> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = jp.getCodec();
        JsonNode node = codec.readTree(jp);

        if (node.isArray()) {
            if (node.elements().hasNext() && !node.elements().next().isArray()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(String.valueOf(node), new TypeReference<List<FilterInfo>>() {
                });
            }
        }
        return new ArrayList<>();
    }
}
