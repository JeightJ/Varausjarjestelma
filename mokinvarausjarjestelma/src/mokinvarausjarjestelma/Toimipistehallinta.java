package mokinvarausjarjestelma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Toimipistehallinta {
	
	Connection yhteys;
	String kuvaus;
	double hinta;
	double alv;
	ArrayList lista;
	
	public Toimipistehallinta(Connection yhteys) {
		this.yhteys = yhteys;
	}
	
	public void lisaaToimipiste(String nimi, String osoite, String toimipaikka, int postinumero, String email, int puhNum) throws SQLException {
		
			PreparedStatement stmt = null;
		
			try {
				stmt = yhteys.prepareStatement("INSERT INTO vp.toimipiste values(nimi, osoite, toimipaikka, postinumero, email, puhNum));");
				stmt.executeUpdate();		
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (stmt != null) {
					stmt.close();
				}
			}
	}
	
	public ArrayList<String> haePalvelut(int toimipisteID) throws SQLException {
		
		Statement stmt = yhteys.createStatement();
		lista = new ArrayList<String>();
		
		try {
			String strSelect = "SELECT palvelu_id, toimipiste_id, nimi FROM vp.palvelu WHERE palvelu.toimipiste_id=toimipiste.toimipiste_id AND toimipiste_id=" + toimipisteID +";";
			ResultSet rset = stmt.executeQuery(strSelect);
			
			if (rset.next()) {
				lista.add(rset.getString(1));
			}
			
			lista.add(rset.getString("nimi"));
			
			return lista;
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}
	
	public String haeToimipiste(String nimi) throws SQLException {
		
		Statement stmt = yhteys.createStatement();
		
		try {
			String strSelect = "SELECT toimipiste_id, nimi, lahiosoite, postitoimipaikka, postinro, email, puhelinnro FROM vp.toimipiste WHERE nimi='" + nimi + "';";
			ResultSet rset = stmt.executeQuery(strSelect);
			
			if (rset.next()) {
				String foundType = rset.getString(1);
			}
	        
	        int toimipisteID = rset.getInt("toimipiste_id");
	        String name = rset.getString("nimi");
	        String osoite = rset.getString("lahiosoite");
	        String toimipaikka = rset.getString("postitoimipaikka");
	        String postinumero = rset.getString("postinro");
	        String sahkoposti = rset.getString("email");
	        String puhNum = rset.getString("puhelinnro");
	         
	        return toimipisteID + ", " + name + "\n" + osoite + ", " + postinumero + " " + toimipaikka + "\n" + sahkoposti + ", " + puhNum;
	        
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return null;
	}
	
	public void muokkaaToimipiste(String nimi, String muokattava2, String uusiArvo2) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = yhteys.prepareStatement("UPDATE vp.toimipiste SET " + muokattava2 + " = '" + uusiArvo2 + "' WHERE nimi = '" + nimi + "';");
			stmt.executeUpdate();		
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
	
	public void poistaToimipiste(String nimi) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = yhteys.prepareStatement("DELETE FROM vp.toimipiste WHERE nimi=" + nimi + ");");
			stmt.executeUpdate();		
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
	
	public void uusiPalvelu(int kohdeID, String palvelu) throws SQLException {
		
		PreparedStatement stmt = null;
		
		switch (palvelu) {
		case "Moottorikelkkasafari": 
			kuvaus = "1 hlö/kelkka";
			hinta = 140.00;
			alv = 5.00;
		case "Hieronta":
			kuvaus = "Klassinen hieronta 60min";
			hinta = 65.00;
			alv = 5.00;
		case "Paintball":
			kuvaus = "1 hlö. Sisältää aseen vuokran";
			hinta = 30.00;
			alv = 2.00;
		case "Intiaaninuotio":
			kuvaus = "Sisältää makkarat.";
			hinta = 10.00;
			alv = 1.00;	
		}
		
		try {
			stmt = yhteys.prepareStatement("INSERT INTO vp.palvelu (toimipiste_id, nimi, tyyppi, kuvaus, hinta, alv) "
											+ "VALUES (" + kohdeID + ", '" + palvelu + "', NULL, '" + kuvaus + "', " + hinta + ", " + alv + ")");
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
