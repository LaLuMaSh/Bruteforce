package ch.lalumash.hacker.beans;


import ch.lalumash.hacker.manager.BruteforceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
public class Beans {
    @Bean
    @Scope("singleton")
    @Autowired
    public BruteforceManager bruteforceManager(Environment environment) {
        return new BruteforceManager(environment);
    }
}
