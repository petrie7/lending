package peter.taylor.lending.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peter.taylor.lending.domain.Investment;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByLoanId(Long loanId);
}
