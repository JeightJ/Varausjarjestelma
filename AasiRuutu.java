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


public class AasiRuutu extends JFrame{

    private JPanel aRuutu;

    public static void main(String[] args)
	{
		/* It posts an event (Runnable)at the end of Swings event list and is
		processed after all other GUI events are processed.*/
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				//try - catch block
				try 
				{
					//Kutsuu AasiRuutu constructoria luomaan meille ekan käyttöliittymän
					AasiRuutu Ruutu = new AasiRuutu();
					Ruutu.setVisible(true);	//yksiselitteinen			
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

/*
    public void AasiRuutu()  //constructori jolla luodaan asiakasruutu, periaatteessa identtinen VillagePooplen samanmoiseen
    {
        setTitle("Asiakas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 500, 750, 750);
		aRuutu = new JPanel();		//Tuossa JPaneeli mihin laitetaan sisältöä / yritetään luoda asiakkaalle oma ruutu
        aRuutu.setBorder(new EmptyBorder(5, 5, 5, 5));		//Borderii tolle ruudulle
		setContentPane(aRuutu);
        aRuutu.setLayout(null); 
        

        //set Label in the frame
		JLabel VPAsia = new JLabel("Alta löydät lisäpalvelut sekä mökkivaihtoehdot");
		//set foreground color to the label
		VPAsia.setForeground(Color.BLUE);
		//set font of that label
        VPAsia.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		//set bound of the label
		VPAsia.setBounds(100, 50, 750, 39);
		//add label to the contentPane
		aRuutu.add(VPAsia);
	} */
}