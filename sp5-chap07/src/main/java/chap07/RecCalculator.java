package chap07;

import org.springframework.stereotype.Component;

@Component
public class RecCalculator implements Calculator{
	
	@Override
	public long factorial(long num) {
		if(num ==0) {
			return 1;
		}else {
			num = num * factorial(num -1);	
		}
		
		return num;
	}
}
