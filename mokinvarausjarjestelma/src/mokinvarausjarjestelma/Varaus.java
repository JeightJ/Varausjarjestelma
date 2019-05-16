package mokinvarausjarjestelma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class Varaus {
	
	Connection yhteys;
	
	// Varaus olion luonti vaatii jo luodun tietokantayhteyden (DBConnection -olio palauttaa True)
	public Varaus(Connection yhteys) {
		this.yhteys = yhteys;
	}
	
	// Varauksen lis‰‰minen t‰ll‰ metodilla, tarvitsee kaikki parametrit
	public void lisaaVaraus(int asiakasID, int toimipisteID, String palvelu, int palveluLkm, Time varausAika, Time varausAlkaa, Time varausLoppuu) throws SQLException {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = yhteys.prepareStatement("INSERT INTO vp.varaus (asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm) "
					+ "VALUES (" + asiakasID + ", " + toimipisteID + ", " + varausAika + ", " + varausAlkaa + ", " + varausLoppuu + ");");
			stmt.executeUpdate();
			// System.out.println("Varaus tehty onnistuneesti."); // Tulostus jos onnistuu (testi)
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
}
