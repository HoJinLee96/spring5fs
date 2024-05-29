package chap02;

public class Greeter {
	
	//고정 문구
	private String format;
	
	//유동 문자열 guest를 고정 문구에 넣어 반환하는 메서드
	public String greet(String guest) {
		return String.format(format, guest);
	}
	
	//고정 문구 세터
	public void setFormat(String format) {
		this.format = format;
	}
}
