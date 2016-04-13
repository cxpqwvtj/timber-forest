package timberforest.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Throws(Exception::class)
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

/**
 * Created by masahiro on 2016/04/14.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("timberforest.app")
open class Application {
}
