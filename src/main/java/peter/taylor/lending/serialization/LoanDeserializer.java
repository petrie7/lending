package peter.taylor.lending.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import peter.taylor.lending.domain.Loan;

import java.io.IOException;

public class LoanDeserializer extends JsonDeserializer<Loan> {
    @Override
    public Loan deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        return new Loan(
                node.get("borrower").textValue(),
                node.get("amount").asDouble()
        );
    }
}
