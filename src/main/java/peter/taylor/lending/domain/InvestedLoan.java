package peter.taylor.lending.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import peter.taylor.lending.serialization.InvestedLoanDeserializer;
import peter.taylor.lending.serialization.InvestedLoanSerializer;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@JsonSerialize(using = InvestedLoanSerializer.class)
@JsonDeserialize(using = InvestedLoanDeserializer.class)
public class InvestedLoan {

    private String borrower;
    private Double loanAmount;
    private Double investmentAmount;

    public InvestedLoan() {
    }

    public InvestedLoan(String borrower, Double loanAmount, Double investmentAmount) {
        this.borrower = borrower;
        this.loanAmount = loanAmount;
        this.investmentAmount = investmentAmount;
    }

    public String borrower() {
        return borrower;
    }

    public Double loanAmount() {
        return loanAmount;
    }

    public Double investmentAmount() {
        return investmentAmount;
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
