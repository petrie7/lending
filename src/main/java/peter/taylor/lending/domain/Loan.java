package peter.taylor.lending.domain;

import javax.persistence.*;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "borrower")
    private String borrower;

    @Column(name = "amount")
    private Double amount;

    public Loan() {
    }

    public Loan(String borrower, Double amount) {
        this.borrower = borrower;
        this.amount = amount;
    }

    public Long id() {
        return id;
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