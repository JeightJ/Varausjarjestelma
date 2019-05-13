/* Tarkoitus luoda Asiakashallinta -olio pääohjelmasta ja sen jälkeen kutsua metodeja käyttöliittymästä */

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
		yhteys = this.yhteys;
	}
	
	// Asiakkaan lisäys tietokantaan
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
	
	// Asiakkaan tietojen hakeminen tietokannasta
	public void haeAsiakas(String etunimi, String sukunimi) throws SQLException {
		
		Statement stmt = yhteys.createStatement();
		
		try {
			String strSelect = "SELECT * FROM vp.asiakas WHERE etunimi=" + etunimi + " AND sukunimi=" + sukunimi + ";";
			ResultSet rset = stmt.executeQuery(strSelect);
			
			System.out.println("The records selected are:");
	         int rowCount = 0;
	         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
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
	
	// Asiakkaan tietojen muokkaus
	public void muokkaaAsiakas(String etunimi, String sukunimi) {
		
		// Seuraava consolessa tapahtuva syötteiden pyytäminen, katsotaan kuinka toteutetaan GUI:ssa
		Scanner lukija = new Scanner(System.in);
		System.out.println("Mitä tietoa muokataan? ");
		System.out.println("1 - Lähiosoite ");
		System.out.println("2 - Postitoimipaikka ");
		System.out.println("3 - Postinumero ");
		System.out.println("4 - Sähköposti ");
		System.out.println("5 - Puhelinnumero ");
		int valittuTieto = lukija.nextInt();
		
		switch (valittuTieto) {
		case 1: 
			System.out.println("Anna uusi osoite: ");
			String uusiOsoite = lukija.nextLine();
			// Näiden jälkeen SQL kysely UPDATE - toiminnolla
		case 2:
			System.out.println("Anna uusi postitoimipaikka: ");
			String uusiToimipaikka = lukija.nextLine();
		case 3:
			System.out.println("Anna uusi postinumero: ");
			String uusiPostinumero = lukija.nextLine();
		case 4:
			System.out.println("Anna uusi email: ");
			String uusiEmail = lukija.nextLine();
		case 5:
			System.out.println("Anna uusi puhelinnumero: ");
			String uusiPuhNum = lukija.nextLine();
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
