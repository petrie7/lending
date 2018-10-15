package peter.taylor.lending.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peter.taylor.lending.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
