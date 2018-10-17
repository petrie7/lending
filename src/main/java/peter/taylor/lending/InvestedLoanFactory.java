package peter.taylor.lending;

import peter.taylor.lending.domain.InvestedLoan;
import peter.taylor.lending.domain.Investment;
import peter.taylor.lending.domain.Loan;

import java.util.List;

public class InvestedLoanFactory {

    public InvestedLoan from(Loan loan, List<Investment> investments) {
        Double investedAmount = 0.0;
        for (Investment investment: investments) {
            investedAmount = investedAmount + investment.amount();
        }

        return new InvestedLoan(
                loan.borrower(),
                loan.amount(),
                investedAmount
        );
    }
}
