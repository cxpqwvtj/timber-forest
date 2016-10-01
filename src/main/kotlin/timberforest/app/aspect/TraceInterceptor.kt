package timberforest.app.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * Created by masahiro on 2016/09/30.
 */
@Component
@Aspect
class TraceInterceptor {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Around("execution(* timberforest.app.controller.*.*(..))")
    fun invoke(proceedingJoinPoint: ProceedingJoinPoint): Any {
        var ret: Any? = null
        try {
            logger.trace("[BEGIN]${proceedingJoinPoint.target.javaClass.name}#${proceedingJoinPoint.signature.name} ${proceedingJoinPoint.args}")
            ret = proceedingJoinPoint.proceed()
            return ret
        } finally {
            logger.trace("[ END ]${proceedingJoinPoint.target.javaClass.name}#${proceedingJoinPoint.signature.name} ${ret}")
        }
    }
}