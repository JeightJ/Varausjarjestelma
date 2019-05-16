package mokinvarausjarjestelma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Asiakashallinta {
	
	Connection yhteys;
	
	// Kontruktori
	public Asiakashallinta(Connection yhteys) {
		this.yhteys = yhteys;
		
	}
	
	// Asiakkaan lisäys tietokantaan
	public void lisaaAsiakas(String etunimi, String sukunimi, String osoite, String postitoimipaikka, String postinumero, String email, int puhNum) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = yhteys.prepareStatement("INSERT INTO vp.asiakas (etunimi, sukunimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro) VALUES ('"+ etunimi + "', '" + sukunimi + "', '" + osoite + "', '" + postitoimipaikka + "', '" + postinumero + "', '" + email + "', " + puhNum + ");");
			stmt.executeUpdate();		
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	// Asiakkaan tietojen hakeminen tietokannasta
	public String haeAsiakas(String etunimi, String sukunimi) throws SQLException {
		
		Statement stmt = yhteys.createStatement();
		
		try {
			String strSelect = "SELECT asiakas_id, etunimi, sukunimi, lahiosoite, postinro, postitoimipaikka, email, puhelinnro FROM vp.asiakas WHERE etunimi='" + etunimi + "' AND sukunimi='" + sukunimi + "';";
			ResultSet rset = stmt.executeQuery(strSelect);
			if (rset.next()){
				String foundType = rset.getString(1);
				}    
	        int asiakasID = rset.getInt("asiakas_id");
	        String fName = rset.getString("etunimi");
	        String lName = rset.getString("sukunimi");
	        String osoite = rset.getString("lahiosoite");
	        String toimipaikka = rset.getString("postitoimipaikka");
	        String postinumero = rset.getString("postinro");
	        String sahkoposti = rset.getString("email");
	        int puhNum = rset.getInt("puhelinnro");
	            
	        return asiakasID + ", " + fName + " " + lName + "\n" + osoite + ", " + postinumero + " " + toimipaikka + "\n" + sahkoposti + ", " + puhNum;
	         
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}
	
	
	// Asiakkaan tietojen muokkaus
	public void muokkaaAsiakas(String etunimi, String sukunimi, String muokattava, String uusiArvo) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = yhteys.prepareStatement("UPDATE vp.asiakas SET " + muokattava + "=" + uusiArvo + " WHERE etunimi='" + etunimi + "' AND sukunimi='" + sukunimi + "';");
			stmt.executeUpdate();		
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	// Asiakkaan kaikkien tietojen poistaminen
	public void poistaAsiakas(String etunimi, String sukunimi) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = yhteys.prepareStatement("DELETE FROM vp.asiakas WHERE etunimi=" + etunimi + " AND sukunimi=" + sukunimi +");");
			stmt.executeUpdate();		
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
}
