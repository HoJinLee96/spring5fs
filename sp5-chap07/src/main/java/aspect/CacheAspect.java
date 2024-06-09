package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CacheAspect {
	
	private Map<Long,Object> cache = new HashMap();
	
//	@Pointcut()
//	public void target() {}
	
	@Around("aspect.ExeTimeAspect.publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long key = (Long)joinPoint.getArgs()[0];
		if(cache.containsKey(key)) {
			return cache.get(key);
		}else {
			Object result =joinPoint.proceed();
			cache.put(key, result);
			return result;
		}
		
		
	}
}
