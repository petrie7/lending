package peter.taylor.lending.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import peter.taylor.lending.domain.InvestedLoan;

import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class InvestmentIntegrationTest {

    private RestTemplate restTemplate;
    private String borrower;
    private Double loanAmount;
    private String lender;
    private Double investmentAmount;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        borrower = "Borrower";
        lender = "Lender";
        loanAmount = 30.0;
        investmentAmount = 10.0;
    }

    @Test
    public void canInvestInALoan() {
        Long loanId = createALoan();

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

    private Long createALoan() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);

        String requestBody = "{\n" +
                "\"borrower\":\"" + borrower + "\"\n," +
                "\"amount\":" + loanAmount + "\n" +
                "}";

        return restTemplate.exchange(
                "http://localhost:8080/create",
                POST,
                new HttpEntity<>(requestBody, httpHeaders),
                Long.class
        ).getBody();
    }

    private InvestedLoan isTheExpectedLoan() {
        return new InvestedLoan(
                borrower,
                loanAmount,
                investmentAmount
        );
    }
}
