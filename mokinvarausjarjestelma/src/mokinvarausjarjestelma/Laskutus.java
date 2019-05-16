package mokinvarausjarjestelma;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Laskutus {
	
	Connection yhteys;
	
	public Laskutus(Connection yhteys) {
		this.yhteys = yhteys;
	}
	
	public String haeNimella(String etunimi, String sukunimi) throws SQLException {
		
		Statement stmt = yhteys.createStatement();
		
		try {
			String strSelect = "SELECT lasku_id, varaus_id, summa, alv FROM vp.lasku WHERE nimi='" + etunimi + " " + sukunimi + "';";
			ResultSet rset = stmt.executeQuery(strSelect);
			if (rset.next()){
				String foundType = rset.getString(1);
				}    
	        int laskuID = rset.getInt("lasku_id");
	        int varausID = rset.getInt("varaus_id");
	        double summa = rset.getDouble("summa");
	        double alv = rset.getDouble("alv");
	            
	        return "Laskun ID: " + laskuID + ", Varauksen ID: " + varausID + "\n Laskun summa: " + summa + ", \n josta ALV:  " + alv;
	         
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}
	
	public String haeVarauksella(int varausID) {
		
		return null;
	}
	
	
}
