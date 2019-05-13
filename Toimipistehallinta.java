import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Toimipistehallinta {
	
	Connection yhteys;
	
	public Toimipistehallinta(Connection yhteys) {
		yhteys = this.yhteys;
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
	
	public void haeToimipiste(String nimi) throws SQLException {
		
		Statement stmt = yhteys.createStatement();
		
		try {
			String strSelect = "SELECT * FROM vp.toimipiste WHERE nimi=" + nimi + ";";
			ResultSet rset = stmt.executeQuery(strSelect);
			
			System.out.println("The records selected are:");
	         int rowCount = 0;
	         while(rset.next()) {   // Move the cursor to the next row, return false if no more row
	            int toimipisteID = rset.getInt("toimipiste_id");
	        	String name = rset.getString("nimi");
	            String osoite = rset.getString("lahiosoite");
	            String toimipaikka = rset.getString("postitoimipaikka");
	            String postinumero = rset.getString("postinro");
	            String sahkoposti = rset.getString("email");
	            int puhNum = rset.getInt("puhelinnro");
	            System.out.println(toimipisteID + ", " + name);
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
	
	public void muokkaaToimipiste(String nimi) throws SQLException {
		
		Scanner lukija = new Scanner(System.in);
		System.out.println("Mitä tietoa muokataan? ");
		System.out.println("1 - Email ");
		System.out.println("2 - Puhelinnumero ");
		int valittuTieto = lukija.nextInt();
		
		if (valittuTieto == 1) {
			System.out.println("Anna uusi email: ");
			String uusiEmail = lukija.nextLine();
			
			PreparedStatement stmt = null;
			
			try {
				stmt = yhteys.prepareStatement("UPDATE vp.toimipiste SET email=" + uusiEmail + " WHERE nimi=" + nimi +");");
				stmt.executeUpdate();		
			} catch (SQLException e) {
				System.out.println(e);
			} finally {
				if (stmt != null) {
					stmt.close();
				}
			}
		}
		if (valittuTieto == 2) {
			System.out.println("Anna uusi puhelinnumero: ");
			int uusiNumero = lukija.nextInt();
			
			PreparedStatement stmt = null;
			
			try {
				stmt = yhteys.prepareStatement("UPDATE vp.toimipiste SET puhelinnro=" + uusiNumero + " WHERE nimi=" + nimi +");");
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
}
