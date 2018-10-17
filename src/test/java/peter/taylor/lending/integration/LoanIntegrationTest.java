package peter.taylor.lending.integration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import peter.taylor.lending.ErrorCapturingResponseHandler;
import peter.taylor.lending.domain.InvestedLoan;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class LoanIntegrationTest extends IntegrationTestBase {

    private ErrorCapturingResponseHandler errorCapturingHandler;

    @Before
    public void setUp() {
        errorCapturingHandler = new ErrorCapturingResponseHandler();
        restTemplate.setErrorHandler(errorCapturingHandler);
    }

    @Test
    public void canRequestAndRetrieveALoan() {
        Long loanId = requestANewLoan();

        ResponseEntity<InvestedLoan> retrievedLoanResponse = retrieveLoan(loanId);

        assertThat(retrievedLoanResponse.getStatusCodeValue(), is(200));
        assertThat(retrievedLoanResponse.getBody(), is(isTheExpectedLoan()));
    }

    @Test
    public void canDeleteALoan() {
        Long loanId = requestANewLoan();

        ResponseEntity<String> response = deleteLoan(loanId);

        assertThat(response.getStatusCodeValue(), is(200));

        retrieveLoan(loanId);

        assertThat(errorCapturingHandler.getLastHttpErrorStatus(), is(NOT_FOUND));
    }
}
