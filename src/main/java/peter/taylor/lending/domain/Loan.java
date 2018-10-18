package peter.taylor.lending.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import peter.taylor.lending.serialization.LoanDeserializer;
import peter.taylor.lending.serialization.LoanSerializer;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@JsonSerialize(using = LoanSerializer.class)
@JsonDeserialize(using = LoanDeserializer.class)
public class Loan {

    private String borrower;
    private Double amount;

    public Loan() {
    }

    public Loan(String borrower, Double amount) {
        this.borrower = borrower;
        this.amount = amount;
    }

    public String borrower() {
        return borrower;
    }

    public Double amount() {
        return amount;
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
