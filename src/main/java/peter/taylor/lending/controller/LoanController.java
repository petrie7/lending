package peter.taylor.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.service.LoanService;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    public LoanController() {
    }

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createLoan(@RequestBody Loan loan) {
        loanService.createLoan(loan);
    }
}
