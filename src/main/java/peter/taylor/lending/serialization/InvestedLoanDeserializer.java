package peter.taylor.lending.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import peter.taylor.lending.domain.InvestedLoan;

import java.io.IOException;

public class InvestedLoanDeserializer extends JsonDeserializer<InvestedLoan> {
    @Override
    public InvestedLoan deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        return new InvestedLoan(
                node.get("borrower").asText(),
                node.get("loanAmount").asDouble(),
                node.get("investmentAmount").asDouble()
        );
    }
}
