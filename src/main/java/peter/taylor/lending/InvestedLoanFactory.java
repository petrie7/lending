package peter.taylor.lending;

import peter.taylor.lending.domain.InvestedLoan;
import peter.taylor.lending.dao.InvestmentDao;
import peter.taylor.lending.dao.LoanDao;

import java.util.List;

public class InvestedLoanFactory {

    public InvestedLoan from(LoanDao loan, List<InvestmentDao> investments) {
        Double investedAmount = 0.0;
        for (InvestmentDao investment: investments) {
            investedAmount = investedAmount + investment.amount();
        }

        return new InvestedLoan(
                loan.borrower(),
                loan.amount(),
                investedAmount
        );
    }
}
