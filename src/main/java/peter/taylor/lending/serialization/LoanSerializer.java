package peter.taylor.lending.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import peter.taylor.lending.domain.Loan;

import java.io.IOException;

public class LoanSerializer extends JsonSerializer<Loan> {

    @Override
    public void serialize(Loan loan, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("borrower", loan.borrower());
        jsonGenerator.writeNumberField("amount", loan.amount());
        jsonGenerator.writeEndObject();
    }
}
