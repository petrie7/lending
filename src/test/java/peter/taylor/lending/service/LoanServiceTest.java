package peter.taylor.lending.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import peter.taylor.lending.InvestedLoanFactory;
import peter.taylor.lending.NoLoanExistsException;
import peter.taylor.lending.domain.Loan;
import peter.taylor.lending.repositories.InvestmentRepository;
import peter.taylor.lending.repositories.LoanRepository;

import static java.util.Collections.emptyList;
import static java.util.Optional.of;
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
    private Loan loan;

    private LoanService loanService;
    private long loanId;

    @Before
    public void setUp() {
        loanService = new LoanService(loanRepository, investmentRepository, investedLoanFactory);
        loanId = 1L;
    }

    @Test
    public void createsALoan() {
        when(loan.id()).thenReturn(1L);
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        loanService.createLoan(loan);

        verify(loanRepository).save(loan);
    }

    @Test
    public void retrievesALoan() {
        when(loanRepository.findById(loanId)).thenReturn(of(loan));
        when(investmentRepository.findByLoanId(loanId)).thenReturn(emptyList());

        loanService.retrieveFor(loanId);

        verify(loanRepository).findById(loanId);
        verify(investmentRepository).findByLoanId(loanId);
        verify(investedLoanFactory).from(loan, emptyList());
    }

    @Test(expected = NoLoanExistsException.class)
    public void throwsWhenNoLoanExists() {
        when(loanRepository.findById(loanId)).thenThrow(new NoLoanExistsException(loanId));

        loanService.retrieveFor(loanId);
        verify(investmentRepository, never()).findByLoanId(any());
        verify(investedLoanFactory, never()).from(any(), any());
    }

}