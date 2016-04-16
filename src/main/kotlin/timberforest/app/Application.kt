package timberforest.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

/**
 * Created by masahiro on 2016/04/14.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("timberforest.app")
@SpringBootApplication
open class Application {
}
