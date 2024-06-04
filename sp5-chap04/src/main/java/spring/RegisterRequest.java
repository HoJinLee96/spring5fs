package spring;

public class RegisterRequest {

	private String email;
	private String name;
	private String password;
	private String confirmPassword;

	
	public RegisterRequest(String email, String name, String password, String confirmPassword) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public boolean passwordToConfirmPassword() {
		return this.password.equals(confirmPassword);
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}


	public String getName() {
		return name;
	}

	
	

}
