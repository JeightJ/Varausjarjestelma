/* Luo yhteyden tietokantaan, jonka jälkeen mahdollista manipuloida tietokannan taulukoita kutsumalla metodeja
esim. Asiakashallinnasta tai Toimipistehallinnasta. */

import java.sql.*;

public class DBConnection {
	
	private Connection yhteys;
	String dbNimi;
	
	public DBConnection() {
	}
	
	public Connection luoYhteys(String username, String password) {
		try {								
			// Database URL syntax = jdbc:mysql://host:port/database?propertyName1=propertyValue1&propertyName2=propertyValue2...
			String url = "jdbc:mysql://localhost:3306/vp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
			// Trying to connect with given data
			yhteys = DriverManager.getConnection(url, username, password);
			return yhteys;
			// System.out.println("Yhdistetty."); // Ilmoittaa jos yhteys muodostettu
		} catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
	
}
