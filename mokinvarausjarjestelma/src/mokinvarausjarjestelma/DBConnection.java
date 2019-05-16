package mokinvarausjarjestelma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DBConnection {
	
	private Connection yhteys;
	
	public DBConnection() {
	}
	
	public Connection luoYhteys() {
		try {								
			// Database URL syntax = jdbc:mysql://host:port/database?propertyName1=propertyValue1&propertyName2=propertyValue2...
			String url = "jdbc:mysql://localhost:3306/vp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			// Trying to connect with given data
			yhteys = DriverManager.getConnection(url, "root", "testi1");
			return yhteys;
			
			// System.out.println("Yhdistetty."); // Ilmoittaa jos yhteys muodostettu
		} catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
	

	
}
