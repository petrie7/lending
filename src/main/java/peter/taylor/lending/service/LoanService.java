package peter.taylor.lending.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.repositories.LoanRepository;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public LoanService() {
    }

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void createLoan(Loan loan) {
        loanRepository.save(loan);
    }
}
