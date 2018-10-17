package peter.taylor.lending;

import org.junit.Test;
import peter.taylor.lending.domain.InvestedLoan;
import peter.taylor.lending.domain.Investment;
import peter.taylor.lending.domain.Loan;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InvestedLoanFactoryTest {

    @Test
    public void createsInvestedLoanFromLoanAndItsInvestments() {
        InvestedLoanFactory investedLoanFactory = new InvestedLoanFactory();

        Loan loan = new Loan("Borrower", 20.0);
        Investment firstInvestment = new Investment(1L, "Lender", 10.0);
        Investment secondInvestment = new Investment(1L, "Second Lender", 5.0);

        InvestedLoan investedLoan = investedLoanFactory.from(loan, asList(firstInvestment, secondInvestment));

        assertThat(investedLoan.borrower(), is(loan.borrower()));
        assertThat(investedLoan.loanAmount(), is(loan.amount()));
        assertThat(
                investedLoan.investmentAmount(),
                is(firstInvestment.amount() + secondInvestment.amount())
        );


    }


}
