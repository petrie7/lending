package peter.taylor.lending.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peter.taylor.lending.dao.InvestmentDao;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<InvestmentDao, Long> {
    List<InvestmentDao> findByLoanId(Long loanId);
}
