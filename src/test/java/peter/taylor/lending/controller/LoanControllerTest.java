package peter.taylor.lending.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import peter.taylor.lending.NoLoanExistsException;
import peter.taylor.lending.domain.InvestedLoan;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.service.LoanService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Long loanId = 1L;

        ResponseEntity<InvestedLoan> responseEntity = loanController.retrieveLoan(loanId);

        assertThat(responseEntity.getStatusCodeValue(), is(200));
        verify(loanService).retrieveFor(loanId);
    }

    @Test
    public void handlesRequestToRetrieveALoanThatDoesNotExist() {
        Long loanId = 1L;
        when(loanService.retrieveFor(loanId)).thenThrow(new NoLoanExistsException(loanId));

        ResponseEntity<InvestedLoan> responseEntity = loanController.retrieveLoan(loanId);

        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

}