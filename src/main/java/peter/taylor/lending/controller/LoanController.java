package peter.taylor.lending.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peter.taylor.lending.domain.InvestedLoan;
import peter.taylor.lending.domain.Investment;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.service.LoanService;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class LoanController {

    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createLoan(@RequestBody Loan loan) {
        return new ResponseEntity<>(loanService.createLoan(loan), OK);
    }

    @RequestMapping(value = "/invest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createInvestment(@RequestBody Investment investment) {
        loanService.createInvestment(investment);
        return new ResponseEntity<>(OK);
    }

    @RequestMapping(value = "/retrieve/{id}", method = RequestMethod.GET)
    public ResponseEntity<InvestedLoan> retrieveLoan(@PathVariable Long id) {
        try {
            InvestedLoan investedLoan = loanService.retrieveFor(id);
            return new ResponseEntity<>(investedLoan, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteLoan(@PathVariable Long id) {
        loanService.delete(id);
        return new ResponseEntity<>(OK);
    }
}
