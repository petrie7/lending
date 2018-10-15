package peter.taylor.lending.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.service.LoanService;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoanControllerTest {

    @Mock
    private LoanService loanService;

    private LoanController loanController;

    @Before
    public void setUp() {
        loanController = new LoanController(loanService);
    }

    @Test
    public void handlesRequestToCreateALoan() {
        Loan loan = new Loan("Borrower", 10.0);

        loanController.createLoan(loan);

        verify(loanService).createLoan(loan);
    }

    @Test
    public void handlesRequestToRetrieveALoan() {
        long loanId = 1L;

        loanController.retrieveLoan(loanId);

        verify(loanService).retrieveFor(loanId);
    }

}