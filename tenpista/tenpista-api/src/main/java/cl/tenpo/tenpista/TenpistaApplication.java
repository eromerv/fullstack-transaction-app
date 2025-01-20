package cl.tenpo.tenpista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "cl.tenpo.tenpista.repository")
@EntityScan(basePackages = "cl.tenpo.tenpista.entity")
public class TenpistaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TenpistaApplication.class, args);
    }
}
