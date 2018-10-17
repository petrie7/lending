package peter.taylor.lending.integration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import peter.taylor.lending.domain.InvestedLoan;

import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class InvestmentIntegrationTest extends IntegrationTestBase {

    private String lender;

    @Before
    public void setUp() {
        lender = "Lender";
        investmentAmount = 10.0;
    }

    @Test
    public void canInvestInALoan() {
        Long loanId = requestANewLoan();

        investsInTheLoanWithThe(loanId);

        assertThat(theLoanAndItsInvestment(loanId), is(isTheExpectedLoan()));
    }

    private InvestedLoan theLoanAndItsInvestment(Long loanId) {
        return restTemplate.getForObject(
                URI.create("http://localhost:8080/retrieve/" + loanId),
                InvestedLoan.class
        );
    }

    private void investsInTheLoanWithThe(Long loanId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);

        String requestBody = "{\n" +
                "\"loanId\":" + loanId + "\n," +
                "\"lender\":\"" + lender + "\"\n," +
                "\"amount\":" + investmentAmount + "\n" +
                "}";

        restTemplate.exchange(
                "http://localhost:8080/invest",
                POST,
                new HttpEntity<>(requestBody, httpHeaders),
                String.class
        );
    }
}
