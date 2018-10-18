package peter.taylor.lending.service;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import peter.taylor.lending.InvestedLoanFactory;
import peter.taylor.lending.NoLoanExistsException;
import peter.taylor.lending.dao.InvestmentDao;
import peter.taylor.lending.dao.LoanDao;
import peter.taylor.lending.domain.Investment;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.repositories.InvestmentRepository;
import peter.taylor.lending.repositories.LoanRepository;

import static java.util.Collections.emptyList;
import static java.util.Optional.of;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;
    @Mock
    private InvestmentRepository investmentRepository;
    @Mock
    private InvestedLoanFactory investedLoanFactory;
    @Mock
    private LoanDao loanDao;

    private LoanService loanService;
    private long loanId;

    @Before
    public void setUp() {
        loanService = new LoanService(loanRepository, investmentRepository, investedLoanFactory);
        loanId = 1L;
    }

    @Test
    public void createsALoan() {
        when(loanDao.id()).thenReturn(loanId);
        when(loanRepository.save(any(LoanDao.class))).thenReturn(loanDao);
        Loan loan = new Loan("Borrower", 10.0);

        Long actualLoanId = loanService.createLoan(loan);

        verify(loanRepository).save(new LoanDao(loan.borrower(), loan.amount()));
        assertThat(actualLoanId, CoreMatchers.is(loanId));
    }

    @Test
    public void retrievesALoan() {
        when(loanRepository.findById(loanId)).thenReturn(of(loanDao));
        when(investmentRepository.findByLoanId(loanId)).thenReturn(emptyList());

        loanService.retrieveFor(loanId);

        verify(loanRepository).findById(loanId);
        verify(investmentRepository).findByLoanId(loanId);
        verify(investedLoanFactory).from(loanDao, emptyList());
    }

    @Test
    public void deletesALoan() {
        loanService.delete(loanId);

        verify(loanRepository).deleteById(loanId);
    }

    @Test
    public void createsAnInvestment() {
        Investment investment = new Investment(
                loanId,
                "Lender",
                10.0
        );

        loanService.createInvestment(investment);

        verify(investmentRepository).save(new InvestmentDao(
                investment.loanId(),
                investment.lender(),
                investment.amount()
        ));

    }

    @Test(expected = NoLoanExistsException.class)
    public void throwsWhenNoLoanExists() {
        when(loanRepository.findById(loanId)).thenThrow(new NoLoanExistsException(loanId));

        loanService.retrieveFor(loanId);
        verify(investmentRepository, never()).findByLoanId(any());
        verify(investedLoanFactory, never()).from(any(), any());
    }

}