package peter.taylor.lending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Bean
    public InvestedLoanFactory investedLoanFactory() {
        return new InvestedLoanFactory();
    }
}
