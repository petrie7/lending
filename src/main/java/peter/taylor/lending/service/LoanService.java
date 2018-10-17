package peter.taylor.lending.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peter.taylor.lending.InvestedLoanFactory;
import peter.taylor.lending.NoLoanExistsException;
import peter.taylor.lending.domain.InvestedLoan;
import peter.taylor.lending.domain.Investment;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.repositories.InvestmentRepository;
import peter.taylor.lending.repositories.LoanRepository;

import java.util.Optional;

@Service
public class LoanService {

    private LoanRepository loanRepository;
    private InvestmentRepository investmentRepository;
    private InvestedLoanFactory investedLoanFactory;

    @Autowired
    public LoanService(LoanRepository loanRepository, InvestmentRepository investmentRepository, InvestedLoanFactory investedLoanFactory) {
        this.loanRepository = loanRepository;
        this.investmentRepository = investmentRepository;
        this.investedLoanFactory = investedLoanFactory;
    }

    public Long createLoan(Loan loan) {
        return loanRepository.save(loan).id();
    }

    public InvestedLoan retrieveFor(Long id) {
        Optional<Loan> loan = loanRepository.findById(id);

        if (loan.isPresent()) {
            return investedLoanFactory.from(
                    loan.get(),
                    investmentRepository.findByLoanId(id)
            );
        } else {
            throw new NoLoanExistsException(id);
        }
    }

    public void delete(Long loanId) {
        loanRepository.deleteById(loanId);
    }

    public void createInvestment(Investment investment) {
        investmentRepository.save(investment);
    }
}
