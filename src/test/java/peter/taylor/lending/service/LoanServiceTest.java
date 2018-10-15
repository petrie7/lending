package peter.taylor.lending.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.repositories.LoanRepository;

import static java.util.Optional.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void createsALoan() {
        Loan loan = new Loan("Borrower", 10.0);

        loanService.createLoan(loan);
    }

    @Test
    public void retrievesALoan() {
        long loanId = 1L;
        when(loanRepository.findById(loanId)).thenReturn(of(new Loan("Borrower", 10.0)));

        loanService.retrieveFor(loanId);

        verify(loanRepository).findById(loanId);
    }

}