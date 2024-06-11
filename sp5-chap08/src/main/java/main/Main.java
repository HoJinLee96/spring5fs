package main;

import java.sql.Connection;
import java.sql.DriverManager;


public class Main {
	public static void main(String[] args) {
		try {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/spring5fs",
				"spring5",
				"00000000");
		}catch(Exception e) {
			e.printStackTrace();
		}
				
	}
}
