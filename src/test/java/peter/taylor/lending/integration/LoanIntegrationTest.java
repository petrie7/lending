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
import peter.taylor.lending.domain.InvestedLoan;

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
        borrower = "Borrower";
        loanAmount = 20.0;
    }

    @Test
    public void canRequestAndRetrieveALoan() {
        Long loanId = requestANewLoan();

        System.out.println("request loan id: " + loanId);

        InvestedLoan retrievedLoan = retrieveLoan(loanId);

        assertThat(retrievedLoan, is(isTheExpectedLoan()));
    }

    private Long requestANewLoan() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);

        String requestBody = "{\n" +
                "\"borrower\":\"" + borrower + "\"\n," +
                "\"amount\":" + loanAmount + "\n" +
                "}";

        return restTemplate.exchange(
                "http://localhost:8080/create",
                HttpMethod.POST,
                new HttpEntity<>(requestBody, httpHeaders),
                Long.class
        ).getBody();
    }

    private InvestedLoan retrieveLoan(Long loanId) {
        return restTemplate.getForObject(
                URI.create("http://localhost:8080/retrieve/" + loanId),
                InvestedLoan.class
        );
    }

    private InvestedLoan isTheExpectedLoan() {
        return new InvestedLoan(
                borrower,
                loanAmount,
                0.0);
    }
}
