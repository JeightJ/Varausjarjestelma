package mokinvarausjarjestelma;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AasiRuutu extends JFrame{
	
	JPanel aRuutu;
	Connection yhteys;
	
	Asiakashallinta uusiAsiakas;
	Varaus uusiVaraus;
	
	DBConnection conn = new DBConnection();
	
	JTextField etunimiKentta, sukunimiKentta, osoiteKentta, toimipaikkaKentta, postinumeroKentta, emailKentta, puhnroKentta;
	String etunimiInput, sukunimiInput, osoiteInput, toimipaikkaInput, postinumeroInput, emailInput, valikkoInput, valikkoInput2, laskutustapa;
	int puhnroInput;
	int kohdeID;
	
	///////////////////////////////////////////
	/* Konstruktori, joka luo asiakasikkunan */
	///////////////////////////////////////////
	public AasiRuutu() {
        setTitle("Asiakas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 750, 750);
		aRuutu = new JPanel();		
        aRuutu.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(aRuutu);
        aRuutu.setLayout(null);
        
        //set Label in the frame
		JLabel VPAsia = new JLabel("Alta l�yd�t lis�palvelut sek� m�kkivaihtoehdot");
		//set foreground color to the label
		VPAsia.setForeground(Color.BLUE);
		//set font of that label
        VPAsia.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		//set bound of the label
		VPAsia.setBounds(100, 50, 750, 39);
		//add label to the contentPane
		aRuutu.add(VPAsia);
		
		JRadioButton paperinen = new JRadioButton("Paperinen lasku");
		paperinen.setActionCommand("paperi");
		JRadioButton sahkoinen = new JRadioButton("S�hk�inen lasku");
		sahkoinen.setActionCommand("sahko");
		
		ButtonGroup group = new ButtonGroup();
	    group.add(paperinen);
	    group.add(sahkoinen);
	    
	    ArrayList<String> palveluLista = new ArrayList<String>();   
        palveluLista.add("Moottorikelkkasafari");
        palveluLista.add("Hieronta");
        palveluLista.add("Paintball");
        palveluLista.add("Intiaaninuotio");
        JComboBox comboBox2 = new JComboBox(palveluLista.toArray());
	    comboBox2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	}
	    });
	    
	    JComboBox comboBox = new JComboBox();
	    comboBox.addItem("Valitse");
	    comboBox.addItem("Lomamaja");
	    comboBox.addItem("Tiipiikyl�");
	    comboBox.addItem("M�kkim�ki");
	    comboBox.addActionListener(new ActionListener() {	        
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    	    valikkoInput = (String) comboBox.getSelectedItem();
	    	    
	    		switch (valikkoInput) {
				case "Lomamaja": kohdeID = 1;
				case "Tiipiikyl�": kohdeID = 2;
				case "M�kkim�ki": kohdeID = 3;
				}
				
				Toimipistehallinta toimha = new Toimipistehallinta(yhteys);
				try {
					ArrayList<String> kaikkiPalvelut = toimha.haePalvelut(kohdeID);
					
			        if (!kaikkiPalvelut.contains("Moottorikelkkasafari")) {
			        	palveluLista.remove("Moottorikelkkasafari");
			        }
			        if (!kaikkiPalvelut.contains("Hieronta")) {
			        	palveluLista.remove("Hieronta");
			        }
			        if (!kaikkiPalvelut.contains("Paintball")) {
			        	palveluLista.remove("Paintball");
			        }
			        if (!kaikkiPalvelut.contains("Intiaaninuotio")) {
			        	palveluLista.remove("Intiaaninuotio");
			        }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	}    
	    });
	    

	    valikkoInput2 = (String) comboBox2.getSelectedItem();
	    
	    
	    
	    JLabel etunimi = new JLabel("Etunimi");
	    JLabel sukunimi = new JLabel("Sukunimi");
	    JLabel osoite = new JLabel("L�hiosoite");
	    JLabel paikka = new JLabel("Postitoimipaikka");
	    JLabel postinumero = new JLabel("Postinumero");
	    JLabel email = new JLabel("S�hk�posti");
	    JLabel puhnro = new JLabel("Puhelinnumero");
	    JLabel lomakohde = new JLabel("Valitse lomakohde");
	    JLabel palvelu = new JLabel("Valitse lis�palvelu");
	    
	    etunimiKentta = new JTextField(20);
	    sukunimiKentta = new JTextField(20);
	    osoiteKentta = new JTextField(20);
	    toimipaikkaKentta = new JTextField(20);
	    postinumeroKentta = new JTextField(20);
	    emailKentta = new JTextField(20);
	    puhnroKentta = new JTextField(20);
	    
	    
	    
		JButton varausNappi = new JButton("Varaa");
		varausNappi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				Connection yhteys = conn.luoYhteys();
				uusiVaraus = new Varaus(yhteys);
				
				laskutustapa = group.getSelection().getActionCommand();
				
				etunimiInput = etunimiKentta.getText();
				sukunimiInput = sukunimiKentta.getText();
				osoiteInput = osoiteKentta.getText();
				toimipaikkaInput = toimipaikkaKentta.getText();
				postinumeroInput = postinumeroKentta.getText();
				emailInput = emailKentta.getText();
				puhnroInput = Integer.parseInt(puhnroKentta.getText());
				
				uusiAsiakas = new Asiakashallinta(yhteys);
				
				
				try {
					uusiAsiakas.lisaaAsiakas(etunimiInput, sukunimiInput, osoiteInput, toimipaikkaInput, postinumeroInput, emailInput, puhnroInput);
					//lisaaVaraus.lisaaVaraus(asiakasID, kohdeID, palvelu, palveluLkm, varausAika, varausAlkaa, varausLoppuu);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//uusiVaraus.lisaaVaraus(int asiakasID, int toimipisteID, String palvelu, int palveluLkm, Time varausAika, Time varausAlkaa, Time varausLoppuu);
				
				//uusiPalvelu.lisaaPalvelut();
			}
		});
		
		///////////////////////////////////////////////////////////////////////
		/* Elementtien sijainnit, koot ja niiden lis��minen k�ytt�liittym��n */
		///////////////////////////////////////////////////////////////////////
		etunimi.setBounds(130, 150, 75, 20);
		aRuutu.add(etunimi);
		sukunimi.setBounds(130, 180, 75, 20);
		aRuutu.add(sukunimi);
		osoite.setBounds(130, 210, 75, 20);
		aRuutu.add(osoite);
		paikka.setBounds(130, 240, 100, 20);
		aRuutu.add(paikka);
		postinumero.setBounds(130, 270, 100, 20);
		aRuutu.add(postinumero);
		email.setBounds(130, 300, 75, 20);
		aRuutu.add(email);
		puhnro.setBounds(130, 330, 100, 20);
		aRuutu.add(puhnro);
		lomakohde.setBounds(130, 360, 120, 20);
		aRuutu.add(lomakohde);
		palvelu.setBounds(130, 390, 120, 20);
		aRuutu.add(palvelu);
		
		etunimiKentta.setBounds(250, 150, 75, 20);
		aRuutu.add(etunimiKentta);
		sukunimiKentta.setBounds(250, 180, 75, 20);
		aRuutu.add(sukunimiKentta);
		osoiteKentta.setBounds(250, 210, 75, 20);
		aRuutu.add(osoiteKentta);
		toimipaikkaKentta.setBounds(250, 240, 75, 20);
		aRuutu.add(toimipaikkaKentta);
		postinumeroKentta.setBounds(250, 270, 75, 20);
		aRuutu.add(postinumeroKentta);
		emailKentta.setBounds(250, 300, 75, 20);
		aRuutu.add(emailKentta);
		puhnroKentta.setBounds(250, 330, 75, 20);
		aRuutu.add(puhnroKentta);
		
		
		comboBox2.setBounds(250, 390, 150, 20);
		aRuutu.add(comboBox2);
		
		comboBox.setBounds(250, 360, 75, 20);
	    aRuutu.add(comboBox);
	    
	    paperinen.setBounds(130, 420, 150, 20);
	    aRuutu.add(paperinen);
	    
	    sahkoinen.setBounds(300, 420, 150, 20);
	    aRuutu.add(sahkoinen);
		
		varausNappi.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));//set font of the Button
		varausNappi.setBounds(130, 480, 125, 25);				//set bounds of the Button
		aRuutu.add(varausNappi);
	}
	
}
