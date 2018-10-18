package peter.taylor.lending;

import org.junit.Test;
import peter.taylor.lending.domain.InvestedLoan;
import peter.taylor.lending.dao.InvestmentDao;
import peter.taylor.lending.dao.LoanDao;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InvestedLoanFactoryTest {

    @Test
    public void createsInvestedLoanFromLoanAndItsInvestments() {
        InvestedLoanFactory investedLoanFactory = new InvestedLoanFactory();

        LoanDao loan = new LoanDao("Borrower", 20.0);
        InvestmentDao firstInvestment = new InvestmentDao(1L, "Lender", 10.0);
        InvestmentDao secondInvestment = new InvestmentDao(1L, "Second Lender", 5.0);

        InvestedLoan investedLoan = investedLoanFactory.from(loan, asList(firstInvestment, secondInvestment));

        assertThat(investedLoan.borrower(), is(loan.borrower()));
        assertThat(investedLoan.loanAmount(), is(loan.amount()));
        assertThat(
                investedLoan.investmentAmount(),
                is(firstInvestment.amount() + secondInvestment.amount())
        );


    }


}
