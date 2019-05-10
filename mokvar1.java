import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.awt.*;

public class mokvar1 {


    
    

public static void main (String[] args){    
  JFrame frame = new JFrame("Test");
  frame.setVisible(true);                                   //kehys näkyviin
  frame.setSize(1000,700);                                     //kehyrin koko
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //sulkee keharin ruksista



  JPanel panel = new JPanel();                      
  frame.add(panel);                                         //tuikkaa paneelin kehyriin
  JButton button = new JButton("Varraa ihelles");              //nappi 1 ja sen teksti
  panel.add(button);                                        // tuikkaa ekan napin paneeliin
  button.addActionListener (new Action1());                     //luo pohjan napin 1 toiminnolle

  JButton button2 = new JButton("Muut palavelut");                 //luo napin 2 
  panel.add(button2);                                              //iskee sen kehykseen
  button2.addActionListener (new Action2());  
  
  


                                                          //sillekkin toiminnan pohoja
}





static class Action1 implements ActionListener {                    //implementtaa actionlisteneri toimintamallin
  public void actionPerformed (ActionEvent e) {                             
    JFrame frame = new JFrame("Varaussivu");
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 400);
    frame.setLocation(430, 100);

    JPanel panel = new JPanel();

    frame.add(panel);

    JLabel lbl = new JLabel("Valihe huvila tahi huussi");                                                       //luo mökkivaraus ikkunan jossa dropdown menussa vaihtoehdot
    lbl.setVisible(true);                                                                                          //minkä mökin haluaa. Tähän osioon tulisi tunkea että osaa 
                                                                                                                //hakea serverin puolelta ne vaihtoehdot mökeistä ja sitten
    panel.add(lbl);                                                                                             //toiminto että ok napista ottaa sen valinnan vastaan.åå

    String[] choices = { "Valinta 1","Valinta 2", "Valinta 3","Valinta 4","Valinta 5","Valinta 6"};

    final JComboBox<String> cb = new JComboBox<String>(choices);

    cb.setVisible(true);
    panel.add(cb);

    JButton btn = new JButton("OK");
    

    JButton returnn = new JButton("Return");
    returnn.addActionListener (new Action3());


    panel.add(btn);
    panel.add(returnn);
  }
}   
static class Action2 implements ActionListener {        
  public void actionPerformed (ActionEvent e) {     
    JFrame frame3 = new JFrame("OKNO 3");                       //kakkosnappulan samat setit ^
    frame3.setVisible(true);
    frame3.setSize(200,200);

    JLabel label = new JLabel("Varrailuhommat");
    JPanel panel = new JPanel();
    frame3.add(panel);
    panel.add(label);
  }
}

  static class Action3 implements ActionListener {
public void actionPerformed (ActionEvent e) {
    JFrame frame1 = new JFrame("Test(1)");
    frame1.setVisible(true);
    frame1.setSize(1000,700);
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

  JPanel panel = new JPanel();                      
  frame1.add(panel);                                         //tuikkaa paneelin kehyriin
  JButton button = new JButton("Varraa ihelles");              //nappi 1 ja sen teksti
  panel.add(button);                                        // tuikkaa ekan napin paneeliin
  button.addActionListener (new Action1());                     //luo pohjan napin 1 toiminnolle

  JButton button2 = new JButton("Muut palavelut");                 //luo napin 2 
  panel.add(button2);                                              //iskee sen kehykseen
  button2.addActionListener (new Action2());  
}

      }
      
  }

