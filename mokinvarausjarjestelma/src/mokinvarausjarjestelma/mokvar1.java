package mokinvarausjarjestelma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

public class mokvar1 {


    
    

public static void main (String[] args){    
  JFrame frame = new JFrame("Test");
  frame.setVisible(true);                                   //kehys näkyviin
  frame.setSize(1000,450);    
  frame.setLocation(450,100);                                //kehyrin koko ja sijainti
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //sulkee keharin ruksista



  JPanel panel = new JPanel();                      
  frame.add(panel);                                         //tuikkaa paneelin kehyriin
  JButton button = new JButton("Varraa ihelles");              //nappi 1 ja sen teksti
  panel.add(button);                                        // tuikkaa ekan napin paneeliin
  button.addActionListener (new Action1());                     //luo pohjan napin 1 toiminnolle

  JButton button2 = new JButton("Muut palavelut");                 //luo napin 2 
  panel.add(button2);                                              //iskee sen kehykseen
  button2.addActionListener (new Action2());  
  
                                                          
}


static class Action1 implements ActionListener {                    //implementtaa actionlisteneri toimintamallin
  public void actionPerformed (ActionEvent e) {                             
    JFrame frame = new JFrame("Varaussivu");
    frame.setVisible(true);
    
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(500, 400);
    frame.setLocation(430, 100);

    JPanel panel = new JPanel();

    frame.add(panel);

    JLabel lbl = new JLabel("Valihe huvila tahi huussi");                                                       //luo mökkivaraus ikkunan jossa dropdown menussa vaihtoehdot
    lbl.setVisible(true);                                                                                          //minkä mökin haluaa. Tähän osioon tulisi tunkea että osaa 
                                                                                                                //hakea serverin puolelta ne vaihtoehdot mökeistä ja sitten
    panel.add(lbl);                                                                                             //toiminto että ok napista ottaa sen valinnan vastaan.

    String[] choices = { "Valinta 1","Valinta 2", "Valinta 3","Valinta 4","Valinta 5","Valinta 6"};

    final JComboBox<String> cb = new JComboBox<String>(choices);

    cb.setVisible(true);
    panel.add(cb);

    JButton btn = new JButton("OK");
    panel.add(btn);
    
  }
}   
static class Action2 implements ActionListener {        
  public void actionPerformed (ActionEvent e) {     
    JFrame frame3 = new JFrame("Palveluvalikko");                       //kakkosnappulan samat setit ^
    frame3.setVisible(true);
    frame3.setSize(500,400);
    frame3.setLocation(490,130);
    frame3.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    JPanel panel2 = new JPanel();


    JCheckBox checkbox1 = new JCheckBox("Palju");
    JCheckBox checkbox2 = new JCheckBox("Liinavaatteet");      
    JCheckBox checkbox3 = new JCheckBox("Siivous");
    JCheckBox checkbox4 = new JCheckBox("Vene");
    JButton tallenna = new JButton("Tallenna");

    panel2.setBorder(BorderFactory.createTitledBorder("Palvelut"));

    panel2.add(checkbox1);
        panel2.add(checkbox2);
        panel2.add(checkbox3);
        panel2.add(checkbox4);
        panel2.add(tallenna);

        frame3.add(panel2);

        frame3.pack();
        frame3.setVisible(true);

    
    JLabel label = new JLabel("Varrailuhommat");
    JPanel panel = new JPanel();


    frame3.add(panel);
    
    panel.add(label);
 
}
  }
 }
