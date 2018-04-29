package pt.iscte.es2;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(ApplicationConstants.APPLICATION_ROOT_PACKAGE)
@EnableJpaRepositories(basePackages = ApplicationConstants.DAO_PACKAGE)
@EnableTransactionManagement
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
	public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean() {
    	return new DozerBeanMapperFactoryBean();
	}
}
