package mokinvarausjarjestelma;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
//Tehdään alkuclassi näytölle
public class VillagePoople extends JFrame {
	
	private JPanel ruutu;	    //Tehdään paneeli meidän käyttöön
	private JPanel asiakasFrame;
	private JPanel yritysFrame;

	// Alla olevalla luodaan se itse ruutu meille käyttöön 
	public VillagePoople() {
		setTitle("Village People varausjärjestelmä");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 750, 750);
		ruutu = new JPanel();								//Tuossa JPaneeli mihin laitetaan sisältöä
        ruutu.setBorder(new EmptyBorder(5, 5, 5, 5));		//Borderii tolle ruudulle
		setContentPane(ruutu);
    	ruutu.setLayout(null);
		
        JButton aNappi = new JButton("Asiakas");			//Luodaan asiakasnappi, lisätään teksti nappiin
		aNappi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//kutsuu classia AasiRuutu				
				asiakasFrame = new JPanel();		//Tuossa JPaneeli mihin laitetaan sisältöä / yritetään luoda asiakkaalle oma ruutu
				AasiRuutu asiakasFrame = new AasiRuutu();
				asiakasFrame.setVisible(true);
				/*asiakasFrame.setTitle("Asiakas");
				asiakasFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				asiakasFrame.setBounds(500, 500, 750, 750);
				        //set Label in the frame
						JLabel VPAsia = new JLabel("Alta loydat lisapalvelut seka mokkivaihtoehdot");
						//set foreground color to the label
						VPAsia.setForeground(Color.BLACK);
						//set font of that label
        				VPAsia.setFont(new Font("Times New Roman", Font.BOLD, 24));
						//set bound of the label
						VPAsia.setBounds(100, 50, 750, 39);
						//add label to the contentPane
						asiakasFrame.add(VPAsia);
				setContentPane(asiakasFrame);
				asiakasFrame.setLayout(null); 
				//set default close operation
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); */
			}
		});

		JButton yNappi = new JButton("Yritys");			//Luodaan yritysnappi, lisätään teksti nappiin
		yNappi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//kutsuu classia AasiRuutu				
				yritysFrame = new JPanel();		//Tuossa JPaneeli mihin laitetaan sisältöä / tekstit yritysnäkymään
				YrRuutu yritysFrame = new YrRuutu();
				yritysFrame.setVisible(true);
				/*yruutu.setTitle("Yritys");
				yruutu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				yruutu.setBounds(500, 500, 750, 750);
				        //set Label in the frame
						JLabel yterve = new JLabel("Tervetuloa yritysnakymaan");
						//set foreground color to the label
						yterve.setForeground(Color.BLACK);
						//set font of that label
        				yterve.setFont(new Font("Times New Roman", Font.BOLD, 24));
						//set bound of the label
						yterve.setBounds(100, 50, 750, 39);
						//add label to the contentPane
						yruutu.add(yterve);
				setContentPane(yruutu);
				yruutu.setLayout(null); 
				//set default close operation
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);*/
			}
		});		

		//Asiakkaan nappi
		aNappi.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));//set font of the Button
		aNappi.setBounds(125, 195, 125, 25);				//set bounds of the Button
		ruutu.add(aNappi);									//add Button into contentPane
		
		//Yrityksen nappi
		yNappi.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));//set font of the Button
		yNappi.setBounds(350, 195, 125, 25);				//set bounds of the Button
		ruutu.add(yNappi);									//add Button into contentPane

		//set Label in the frame
		JLabel VPTerv = new JLabel("Tervetuloa Village People Mokkivarausjarjestelmaan!");
		//set foreground color to the label
		VPTerv.setForeground(Color.BLACK);
		//set font of that label
        VPTerv.setFont(new Font("Times New Roman", Font.BOLD, 24));
		//set bound of the label
		VPTerv.setBounds(100, 50, 750, 39);
		//add label to the contentPane
		ruutu.add(VPTerv);
	}
}
