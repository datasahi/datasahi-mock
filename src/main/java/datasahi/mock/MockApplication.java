package datasahi.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class MockApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockApplication.class, args);
    }

}
