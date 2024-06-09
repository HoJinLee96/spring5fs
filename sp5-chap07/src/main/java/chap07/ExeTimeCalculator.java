package chap07;

public class ExeTimeCalculator implements Calculator{
	private Calculator cal;
	
	public ExeTimeCalculator(Calculator cal) {
		this.cal = cal;
	}
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		long result = cal.factorial(num);
		long end = System.nanoTime();
		System.out.printf("결과값 = %d, 실행시간 = %d \n",result,start-end);
		return 0;
	}
	
}
