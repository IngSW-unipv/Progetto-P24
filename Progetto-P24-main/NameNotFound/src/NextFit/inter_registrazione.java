package NextFit;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class inter_registrazione extends JFrame
{
	public inter_registrazione()
	{
		setSize(360,640);
		setLayout(new BorderLayout());
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setLocation(screenWidth/4,screenHeight/4);
		setTitle("NextFit");
		reg_button b=new reg_button();
		Container c = getContentPane();
		c.add(b);
	}
	
	
	
	public static void main(String[] args) 
	{
		inter_registrazione f = new inter_registrazione();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
