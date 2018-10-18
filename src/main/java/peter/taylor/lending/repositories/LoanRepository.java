package peter.taylor.lending.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peter.taylor.lending.dao.LoanDao;

public interface LoanRepository extends JpaRepository<LoanDao, Long> {
}
