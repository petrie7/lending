package peter.taylor.lending.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peter.taylor.lending.domain.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
