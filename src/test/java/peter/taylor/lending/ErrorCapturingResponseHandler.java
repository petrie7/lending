package peter.taylor.lending;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class ErrorCapturingResponseHandler implements ResponseErrorHandler {

    private HttpStatus lastHttpErrorStatus;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        lastHttpErrorStatus = response.getStatusCode();
    }

    public HttpStatus getLastHttpErrorStatus() {
        return lastHttpErrorStatus;
    }
}
