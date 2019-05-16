package mokinvarausjarjestelma;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		VillagePoople ruutu = new VillagePoople();
		ruutu.setVisible(true);
	
	}

}

/*
 * Torstain 16.5. ja perjantain 17.5. harjoitustunneilla kaikki ryhmät esittelevät tuotoksensa seuraavan koreografian mukaisesti:
1. Hae toimipiste ja muuta sen tietoja, TEHTY
2. Hae asiakas ja muuta sen tietoja, TEHTY
3. Lisää (toimipisteelle) palvelu, TEHTY
4. Lisää asiakkaalle varaus, jossa on vähintään 2 palvelua (mökki+muu), 
5. Esittele lyhyesti sovelluksen muut toiminnot (laskutus & raportit). LASKUN HAKEMINEN ASIAKKAAN NIMELLÄ TEHTY
Yhtä ryhmää kohti aikaa ei tulisi kulua yli 10 minuuttia. 
Esittely joko TB247:n koneella / omalla koneella TB247:ssa tai Collaboraten välityksellä etänä. 
Demoympäristö on syytä testata toimivaksi ja syöttää tietokantaan tarpeellinen määrä testimateriaalia jo ennen ryhmän omaa esitysvuoroa.
Ryhmät R1-R10 esittävät demonsa torstaina ja R11-R20 perjantaina.
*/
