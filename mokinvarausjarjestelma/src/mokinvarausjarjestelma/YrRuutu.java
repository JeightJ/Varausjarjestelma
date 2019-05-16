package mokinvarausjarjestelma;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class YrRuutu extends JFrame{

    private JPanel yRuutu;
    DBConnection conn = new DBConnection();
    Varaus uusiVaraus;
    JTextField etunimiKentta, sukunimiKentta, toimipisteKentta;
    JTextArea tulosKentta;
    String etunimiInput, sukunimiInput, toimipisteInput, valikkoInput, valittuKohde;
    int kohdeID;

    public YrRuutu() {
        setTitle("Yritys");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 750, 750);
		yRuutu = new JPanel();		
        yRuutu.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(yRuutu);
        yRuutu.setLayout(null);
        
        //set Label in the frame
		JLabel VPAsia = new JLabel("Hallinnoi asiakas-, toimipiste-, ja varaustietoja");
		//set foreground color to the label
		VPAsia.setForeground(Color.BLUE);
		//set font of that label
        VPAsia.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		//set bound of the label
		VPAsia.setBounds(100, 50, 750, 39);
		//add label to the contentPane
		yRuutu.add(VPAsia);
		
		JLabel VPOhje = new JLabel("Valitse tarkasteltava kohde");
		VPOhje.setForeground(Color.BLACK);
		VPOhje.setFont(new Font("Times New Roman", Font.BOLD, 14));
		VPOhje.setBounds(100, 120, 200, 20);
		yRuutu.add(VPOhje);
		
		JComboBox comboBox = new JComboBox();
	    comboBox.addItem("Valitse");
	    comboBox.addItem("Hae tiedot");
	    comboBox.addItem("Muokkaa");
	    comboBox.addItem("Poista");
	    comboBox.addActionListener(new ActionListener() {	        
	    	public void actionPerformed(ActionEvent arg0) {
	    	}
	    
	    });
		
        JRadioButton valitseAsiakas = new JRadioButton("Asiakas");
        valitseAsiakas.setActionCommand("Asiakas");
        valitseAsiakas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	etunimiKentta.setEditable(true);
            	sukunimiKentta.setEditable(true);
                toimipisteKentta.setEditable(false);
            }
        });
        JRadioButton valitseToimipiste = new JRadioButton("Toimipiste");
        valitseToimipiste.setActionCommand("Toimipiste");
        valitseToimipiste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etunimiKentta.setEditable(false);
                sukunimiKentta.setEditable(false);
                toimipisteKentta.setEditable(true);
            }
        });
        JRadioButton valitseVaraus = new JRadioButton("Varaus");
        valitseVaraus.setActionCommand("Varaus");
        valitseVaraus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	etunimiKentta.setEditable(true);
            	sukunimiKentta.setEditable(true);
                toimipisteKentta.setEditable(false);
            }
        });
        
        JRadioButton lisaaPalvelu = new JRadioButton("Lisää palvelu");
        lisaaPalvelu.setActionCommand("Lisää palvelu");
        lisaaPalvelu.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		etunimiKentta.setEditable(false);
        		sukunimiKentta.setEditable(false);
        		toimipisteKentta.setEditable(true);
        	}
        });
        
		ButtonGroup group = new ButtonGroup();
	    group.add(valitseAsiakas);
	    group.add(valitseToimipiste);
	    group.add(valitseVaraus);
	    group.add(lisaaPalvelu);
	        
	    JLabel etunimi = new JLabel("Etunimi");
	    JLabel sukunimi = new JLabel("Sukunimi");
	    JLabel toimipisteNimi = new JLabel("Toimipisteen nimi");
	    JLabel valikko = new JLabel("Valitse toiminto");
	    
	    etunimiKentta = new JTextField(20);
	    sukunimiKentta = new JTextField(20);
	    toimipisteKentta = new JTextField(20);
	    tulosKentta = new JTextArea(10, 40);
	    
	    JButton laskuNappi = new JButton("LASKUTUS");
		laskuNappi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Connection yhteys = conn.luoYhteys();
				Laskutus reskontra = new Laskutus(yhteys);
				
				JTextField eNimiKentta = new JTextField();
				JTextField sNimiKentta = new JTextField();
				Object[] viesti = {
				    "Etunimi ", eNimiKentta,
				    "Sukunimi ", sNimiKentta
				};
				
				JOptionPane.showMessageDialog(yRuutu, viesti, "Laskutus", JOptionPane.QUESTION_MESSAGE);
				
				String eNimi = eNimiKentta.getText();
				String sNimi = sNimiKentta.getText();
				try {
					String loydetty = reskontra.haeNimella(eNimi, sNimi);
					tulosKentta.setText(loydetty);
					JOptionPane.showMessageDialog(yRuutu, 
							"Lasku haettu.",
							"Ilmoitus",
							JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
	    
		JButton toimintoNappi = new JButton("OK");
		toimintoNappi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				Connection yhteys = conn.luoYhteys();
				Asiakashallinta asha = new Asiakashallinta(yhteys);
				Toimipistehallinta toimha = new Toimipistehallinta(yhteys);
				
				valittuKohde = group.getSelection().getActionCommand();
				etunimiInput = etunimiKentta.getText();
				sukunimiInput = sukunimiKentta.getText();
				toimipisteInput = toimipisteKentta.getText();
				valikkoInput = (String) comboBox.getSelectedItem();
				
				if (valittuKohde == "Asiakas" && valikkoInput == "Hae tiedot") {
					try {
						String asiakasTiedot = asha.haeAsiakas(etunimiInput, sukunimiInput);
						tulosKentta.setText(asiakasTiedot);
						JOptionPane.showMessageDialog(yRuutu,
							    "Asiakastiedot haettu.",
							    "Ilmoitus",
							    JOptionPane.WARNING_MESSAGE);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (valittuKohde == "Asiakas" && valikkoInput == "Muokkaa") {
					try {
						String[] lista = {"lahiosoite", "postitoimipaikka", "postinro", "email", "puhelinnro"};
						
						String muokattava = (String) JOptionPane.showInputDialog(null, "Mitä tietoa muokataan?", "Asiakastiedot", JOptionPane.QUESTION_MESSAGE, null, lista, lista[0]);
						String uusiArvo = JOptionPane.showInputDialog("Anna uusi arvo: ");
						
						asha.muokkaaAsiakas(etunimiInput, sukunimiInput, muokattava, uusiArvo);
						
						JOptionPane.showMessageDialog(yRuutu,
							    "Asiakastietoa muutettu.",
							    "Ilmoitus",
							    JOptionPane.WARNING_MESSAGE);
						
					} catch (Exception e){
						e.printStackTrace();
					}
				}
				
				if (valittuKohde == "Toimipiste" && valikkoInput == "Hae tiedot") {
					try {
						String toimipisteTiedot = toimha.haeToimipiste(toimipisteInput);
						tulosKentta.setText(toimipisteTiedot);
						JOptionPane.showMessageDialog(yRuutu,
								"Toimipisteen tiedot haettu.",
								"Ilmoitus",
								JOptionPane.WARNING_MESSAGE);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				if (valittuKohde == "Toimipiste" && valikkoInput == "Muokkaa") {
					try {
						String[] lista2 = {"email", "puhelinnro"};
						
						String muokattava = (String) JOptionPane.showInputDialog(null, "Mitä tietoa muokataan?", "Toimipistetiedot", JOptionPane.QUESTION_MESSAGE, null, lista2, lista2[0]);
						String uusiArvo = JOptionPane.showInputDialog("Anna uusi arvo: ");
						
						toimha.muokkaaToimipiste(toimipisteInput, muokattava, uusiArvo);
						
						JOptionPane.showMessageDialog(yRuutu, 
								"Toimipisteen tietoja muutettu.",
								"Ilmoitus",
								JOptionPane.WARNING_MESSAGE);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				if (valittuKohde == "Lisää palvelu") {
					try {
						String[] lista3 = {"Moottorikelkkasafari", "Hieronta", "Paintball", "Intiaaninuotio"};
						
						String lisattava = (String) JOptionPane.showInputDialog(null, "Mikä palvelu lisätään?", "Palvelun lisääminen", JOptionPane.QUESTION_MESSAGE, null, lista3, lista3[0]);
						
						switch (toimipisteInput) {
						case "Lomamaja": kohdeID = 1;
						case "Tiipiikylä": kohdeID = 2;
						case "Mökkimäki": kohdeID = 3;
						}
						toimha.uusiPalvelu(kohdeID, lisattava);
						
						JOptionPane.showMessageDialog(yRuutu, 
								"Toimipisteelle lisättiin uusi palvelu.",
								"Ilmoitus",
								JOptionPane.WARNING_MESSAGE);
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
		
		/* Elementtien sijainnit käyttöliittymässä ja lisäys käyttöiittymään */
		
		valitseAsiakas.setBounds(130, 150, 75, 20);
		yRuutu.add(valitseAsiakas);
		
		valitseToimipiste.setBounds(270, 150, 125, 20);
		yRuutu.add(valitseToimipiste);
		
		valitseVaraus.setBounds(410, 150, 75, 20);
		yRuutu.add(valitseVaraus);
		
		lisaaPalvelu.setBounds(550, 150, 125, 20);
		yRuutu.add(lisaaPalvelu);
		
		etunimi.setBounds(130, 200, 75, 20);
		yRuutu.add(etunimi);
		sukunimi.setBounds(130, 230, 75, 20);
		yRuutu.add(sukunimi);
		toimipisteNimi.setBounds(130, 260, 100, 20);
		yRuutu.add(toimipisteNimi);
		valikko.setBounds(130, 310, 100, 20);
		yRuutu.add(valikko);
		
		etunimiKentta.setBounds(250, 200, 75, 20);
		yRuutu.add(etunimiKentta);
		sukunimiKentta.setBounds(250, 230, 75, 20);
		yRuutu.add(sukunimiKentta);
		toimipisteKentta.setBounds(250, 260, 75, 20);
		yRuutu.add(toimipisteKentta);
		tulosKentta.setBounds(400, 200, 200, 100);
		yRuutu.add(tulosKentta);
		
		comboBox.setBounds(250, 310, 75, 20);
	    yRuutu.add(comboBox);
		
		toimintoNappi.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));//set font of the Button
		toimintoNappi.setBounds(130, 370, 150, 40);				//set bounds of the Button
		yRuutu.add(toimintoNappi);
		
		laskuNappi.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));//set font of the Button
		laskuNappi.setBounds(400, 370, 150, 40);				//set bounds of the Button
		yRuutu.add(laskuNappi);
	}
}