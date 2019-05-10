/* Tarkoitus luoda Asiakashallinta -olio pääohjelmasta ja sen jälkeen kutsua metodeja käyttöliittymästä */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Asiakashallinta {
	
	Connection yhteys;
	
	// Konstruktori, saa parametrinä tietokantayhteyden
	public Asiakashallinta(Connection yhteys) {
		yhteys = this.yhteys;
	}
	
	// Asiakkaan lisäys tietokantaan, parametreinä kaikki
	public void lisaaAsiakas(String etunimi, String sukunimi, String osoite, String postitoimipaikka, String postinumero, String email, int puhNum) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = yhteys.prepareStatement("INSERT INTO vp.asiakas values(etunimi, sukunimi, osoite, postitoimipaikka, postinumero, email, puhNum));");
			stmt.executeUpdate();		
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	// Asiakkaan tietojen hakeminen tietokannasta, parametreinä etunimi ja sukunimi
	public void haeAsiakas(String etunimi, String sukunimi) throws SQLException {
		
		Statement stmt = yhteys.createStatement();
		
		try {
			String strSelect = "SELECT * FROM vp.asiakas WHERE etunimi=" + etunimi + " AND sukunimi=" + sukunimi + ";";
			ResultSet rset = stmt.executeQuery(strSelect);
			
			System.out.println("Tulokset:");
	         int rowCount = 0;
	         while(rset.next()) {   // Siirtää kursorin seuraavalle riville, palauttaa Falsen jos ei enempää rivejä
	            int asiakasID = rset.getInt("asiakas_id");
	        	String fName = rset.getString("etunimi");
	            String lName = rset.getString("sukunimi");
	            String osoite = rset.getString("lahiosoite");
	            String toimipaikka = rset.getString("postitoimipaikka");
	            String postinumero = rset.getString("postinumero");
	            String sahkoposti = rset.getString("email");
	            int puhNum = rset.getInt("puhelinnro");
	            System.out.println(asiakasID + ", " + fName + ", " + lName);
	            System.out.println(osoite + ", " + postinumero + " " + toimipaikka);
	            System.out.println(sahkoposti);
	            System.out.println(puhNum);
	            ++rowCount;
	         }
	         System.out.println("Haku suoritettu.");
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
  // Metodi asiakkaan poistamiseen, parametreinä etunimi ja sukunimi
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
