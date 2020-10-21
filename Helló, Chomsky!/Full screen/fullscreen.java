import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class fullscreen extends JPanel{

	String uzenet = "Fullscreen";

	public void paint(Graphics g)
	{
    	g.setFont(new Font("TimesRoman", Font.BOLD, 56));
    	g.setColor(Color.gold);
		g.drawString(uzenet, 540, 540);

	}

	public static void main(String[]args)
	{
		JFrame frame = new JFrame("Full screen program");

		frame.getContentPane().add(new fullscreen());
		
		frame.setSize(1920, 1080);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
}	
