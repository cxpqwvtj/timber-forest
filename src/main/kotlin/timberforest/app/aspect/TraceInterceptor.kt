package timberforest.app.aspect

import ch.qos.logback.classic.pattern.TargetLengthBasedClassNameAbbreviator
import ch.qos.logback.core.pattern.SpacePadder
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

    @Around("execution(* timberforest.app.controller..*.*(..))")
    fun invoke(proceedingJoinPoint: ProceedingJoinPoint): Any? {
        var ret: Any? = null
        val className = TargetLengthBasedClassNameAbbreviator(20).abbreviate(proceedingJoinPoint.target.javaClass.name)
        try {
            logger.trace("[BEGIN]${className}#${proceedingJoinPoint.signature.name} ${proceedingJoinPoint.args}")
            ret = proceedingJoinPoint.proceed()
            return ret
        } finally {
            logger.trace("[ END ]${className}#${proceedingJoinPoint.signature.name} ${ret}")
        }
    }
}