package peter.taylor.lending.integration;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import peter.taylor.lending.domain.InvestedLoan;

import java.net.URI;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public abstract class IntegrationTestBase {

    protected RestTemplate restTemplate;
    protected String borrower;
    protected Double loanAmount;
    protected Double investmentAmount;

    @Before
    public void init() {
        restTemplate = new RestTemplate();
        borrower = "Borrower";
        loanAmount = 20.0;
        investmentAmount = 0.0;
    }


    protected Long requestANewLoan() {
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

    protected ResponseEntity<String> deleteLoan(Long loanId) {
        return restTemplate.exchange(
                URI.create("http://localhost:8080/delete/" + loanId),
                DELETE,
                EMPTY,
                String.class
        );
    }

    protected ResponseEntity<InvestedLoan> retrieveLoan(Long loanId) {
        return restTemplate.exchange(
                URI.create("http://localhost:8080/retrieve/" + loanId),
                GET,
                EMPTY,
                InvestedLoan.class
        );
    }

    protected InvestedLoan isTheExpectedLoan() {
        return new InvestedLoan(
                borrower,
                loanAmount,
                investmentAmount);
    }
}
