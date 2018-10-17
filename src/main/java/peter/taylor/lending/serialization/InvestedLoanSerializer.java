package peter.taylor.lending.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import peter.taylor.lending.domain.InvestedLoan;

import java.io.IOException;

public class InvestedLoanSerializer extends JsonSerializer<InvestedLoan> {
    @Override
    public void serialize(InvestedLoan loan, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("borrower", loan.borrower());
        jsonGenerator.writeNumberField("loanAmount", loan.loanAmount());
        jsonGenerator.writeNumberField("investmentAmount", loan.investmentAmount());
        jsonGenerator.writeEndObject();
    }
}
