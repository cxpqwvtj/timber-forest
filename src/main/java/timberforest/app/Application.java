package timberforest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("timberforest.app")
public class Application {
    public static void main(String[] args) throws Exception {
        // AnsiOutput.setEnabled(Enabled.ALWAYS);
        SpringApplication.run(Application.class, args);
    }
}
