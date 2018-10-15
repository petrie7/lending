package peter.taylor.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.service.LoanService;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class LoanController {

    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createLoan(@RequestBody Loan loan) {
        loanService.createLoan(loan);
    }

    @RequestMapping(value = "/retrieve/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<Loan> retrieveLoan(@PathVariable Long id) {
        return new ResponseEntity<>(loanService.retrieveFor(id), OK);
    }
}
