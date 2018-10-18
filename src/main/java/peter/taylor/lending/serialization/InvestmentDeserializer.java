package peter.taylor.lending.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import peter.taylor.lending.domain.Investment;

import java.io.IOException;

public class InvestmentDeserializer extends JsonDeserializer<Investment> {
    @Override
    public Investment deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        return new Investment(
                node.get("loanId").asLong(),
                node.get("lender").textValue(),
                node.get("amount").asDouble()
        );
    }
}
