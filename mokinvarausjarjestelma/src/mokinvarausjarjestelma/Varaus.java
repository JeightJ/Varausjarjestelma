package mokinvarausjarjestelma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Varaus {
	
	Connection yhteys;
	
	// Varaus olion luonti vaatii jo luodun tietokantayhteyden (DBConnection -olio palauttaa True)
	public Varaus(Connection yhteys) {
		this.yhteys = yhteys;
	}
	
	// Varauksen lisääminen tällä metodilla, tarvitsee kaikki parametrit
	public void lisaaVaraus(int asiakasID, int toimipisteID, String varausAlku, String varausLoppu) throws SQLException {
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String varausAika = formatter.format(date);
		String vahvistusAika = formatter.format(date);
		String varausAlkaa = varausAlku + " 15:00:00";
		String varausLoppuu = varausLoppu + " 12:00:00";
		PreparedStatement stmt = null;
		
		try {
			stmt = yhteys.prepareStatement("INSERT INTO vp.varaus (asiakas_id, toimipiste_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm) "
					+ "VALUES (" + asiakasID + ", " + toimipisteID + ", '" + varausAika + "', '" + vahvistusAika + "', '" + varausAlkaa + "', '" + varausLoppuu + "');");
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
