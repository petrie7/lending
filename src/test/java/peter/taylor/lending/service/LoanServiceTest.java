package peter.taylor.lending.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.repositories.LoanRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    private LoanService loanService;

    @Before
    public void setUp() {
        loanService = new LoanService(loanRepository);
    }

    @Test
    public void createsLoan() {
        Loan loan = new Loan("Borrower", 10.0);
        loanService.createLoan(loan);

        verify(loanRepository).save(loan);
    }

}