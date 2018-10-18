package peter.taylor.lending.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import peter.taylor.lending.NoLoanExistsException;
import peter.taylor.lending.domain.InvestedLoan;
import peter.taylor.lending.domain.Investment;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.service.LoanService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanControllerTest {

    @Mock
    private LoanService loanService;

    private LoanController loanController;
    private Long loanId;


    @Before
    public void setUp() {
        loanController = new LoanController(loanService);
        loanId = 1L;
    }

    @Test
    public void handlesRequestToCreateALoan() {
        Loan loan = new Loan("Borrower", 10.0);

        ResponseEntity<Long> response = loanController.createLoan(loan);

        assertThat(response.getStatusCodeValue(), is(200));
        assertThat(response.getBody(), is(notNullValue()));
        verify(loanService).createLoan(loan);
    }

    @Test
    public void handlesRequestToInvestInALoan() {
        Investment investment = new Investment(
                loanId,
                "Lender",
                10.0
        );
        ResponseEntity<String> response = loanController.createInvestment(investment);

        assertThat(response.getStatusCodeValue(), is(200));
        verify(loanService).createInvestment(investment);
    }

    @Test
    public void handlesRequestToRetrieveALoan() {
        ResponseEntity<InvestedLoan> responseEntity = loanController.retrieveLoan(loanId);

        assertThat(responseEntity.getStatusCodeValue(), is(200));
        verify(loanService).retrieveFor(loanId);
    }

    @Test
    public void handlesRequestToDeleteALoan() {
        ResponseEntity<String> response = loanController.deleteLoan(loanId);

        assertThat(response.getStatusCodeValue(), is(200));
        verify(loanService).delete(loanId);
    }

    @Test
    public void handlesRequestToRetrieveALoanThatDoesNotExist() {
        when(loanService.retrieveFor(loanId)).thenThrow(new NoLoanExistsException(loanId));

        ResponseEntity<InvestedLoan> responseEntity = loanController.retrieveLoan(loanId);

        assertThat(responseEntity.getStatusCodeValue(), is(404));
    }

}