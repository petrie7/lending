package peter.taylor.lending.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import peter.taylor.lending.serialization.InvestmentDeserializer;

import javax.persistence.*;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@Entity
@JsonDeserialize(using = InvestmentDeserializer.class)
public class Investment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "loan_id")
    private Long loanId;

    @Column
    private String lender;

    @Column
    private Double amount;

    public Investment() {
    }

    public Investment(Long loanId, String lender, Double amount) {
        this.loanId = loanId;
        this.lender = lender;
        this.amount = amount;
    }

    public Long id() {
        return id;
    }

    public Long loanId() {
        return loanId;
    }

    public String lender() {
        return lender;
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
