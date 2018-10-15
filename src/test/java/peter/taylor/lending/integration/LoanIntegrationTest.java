package peter.taylor.lending.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import peter.taylor.lending.domain.Loan;

import java.net.URI;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class LoanIntegrationTest {

    private RestTemplate restTemplate;
    private String borrower;
    private double loanAmount;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
        borrower = "Pete";
        loanAmount = 20.0;
    }

    @Test
    public void canCreateALoan() {
        requestANewLoan();

        assertThat(theLoan(), is(isTheExpectedLoan()));
    }

    private void requestANewLoan() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);

        String requestBody = "{\n" +
                "\"borrower\":\"" + borrower + "\"\n," +
                "\"amount\":" + loanAmount + "\n" +
                "}";

        restTemplate.exchange(
                "http://localhost:8080/create",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, httpHeaders),
                String.class
        );
    }

    private Loan theLoan() {
        return restTemplate.getForObject(
                URI.create("http://localhost:8080/retrieve/1"),
                Loan.class
        );
    }

    private Loan isTheExpectedLoan() {
        return new Loan(borrower, loanAmount);
    }
}
