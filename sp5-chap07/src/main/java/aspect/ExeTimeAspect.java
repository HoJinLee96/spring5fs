package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class ExeTimeAspect {
	
	@Pointcut("execution(public * chap07..*(..))")
	public void publicTarget() {}
	
	@Around("publicTarget()")	
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		Object result = joinPoint.proceed();
		long end = System.nanoTime();
		System.out.printf("%s 실행 시간 = %d \n",joinPoint.getSignature().getDeclaringType(),start-end);
		return result;
	}
}
