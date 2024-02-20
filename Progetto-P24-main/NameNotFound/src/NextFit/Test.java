package NextFit;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Test 
{
	public static void main(String[] args) 
	{
		inter_registrazione f = new inter_registrazione();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		Palestra NextFit = new Palestra(6,3);
		/*Cliente u = new Cliente("pino" , "pino", "pino", "pino", 19);
		Cliente u1 = new Cliente("pino" , "pino", "pino", "pino", 19);
		Cliente u2 = new Cliente("cino" , "pino", "pino", "pino", 1);
		Cliente u3 = new Cliente("cino" , "pino", "pino", "pino", 19);
		Cliente u4 = new Cliente("lino" , "pino", "pino", "pino", 19);
		Cliente u5 = new Cliente("tino" , "pino", "pino", "pino", 19);
		Cliente u6 = new Cliente("wino" , "pino", "pino", "pino", 19);
		Cliente u7 = new Cliente("qino" , "pino", "pino", "pino", 19);
		Cliente u8 = new Cliente("dino" , "pino", "pino", "pino", 19);
		Cliente u9 = new Cliente("mino" , "pino", "pino", "pino", 19);
		Cliente u10 = new Cliente("sino" , "pino", "pino", "pino", 19);
		
		NextFit.registraCliente(u);
		NextFit.registraCliente(u1);
		NextFit.registraCliente(u2);
		NextFit.registraCliente(u3);
		NextFit.registraCliente(u4);
		NextFit.registraCliente(u5);
		NextFit.registraCliente(u6);
		NextFit.registraCliente(u7);
		NextFit.registraCliente(u8);
		NextFit.registraCliente(u9);
		NextFit.registraCliente(u10);*/
		
		NextFit.registraDipendente(NextFit.creaDipendente("pino" , "pino", "pino", "pino", 19, 98919, "PersonalTrainer"));
		NextFit.registraDipendente(NextFit.creaDipendente("wino" , "pino", "pino", "pino", 19, 98919, "Dietista"));
		NextFit.registraDipendente(NextFit.creaDipendente("pino" , "pino", "pino", "pino", 19, 98919, "PersonalTrainer"));
		NextFit.registraDipendente(NextFit.creaDipendente("cino" , "pino", "pino", "pino", 19, 98919, "Corsista"));
		NextFit.registraDipendente(NextFit.creaDipendente("tino" , "pino", "pino", "pino", 19, 98919, "PersonalTrainer"));


		 class reg_button extends JPanel
		{
			JTextField nome,cognome,età,mail;
			JPasswordField password;
			
			public reg_button()
			{
				this.setLayout(new BorderLayout());
				JPanel formPanel = new JPanel(new GridLayout(5,1, 5, 5));
		        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		       
		        JLabel usernameLabel = new JLabel("Nome:");
		        JLabel passwordLabel = new JLabel("Password:");
		        JLabel emailLabel = new JLabel("Mail:");
		        JLabel surnameLabel = new JLabel("Cognome:");
		        JLabel ageLabel = new JLabel("Età:");
		        
		        
		        
		        nome = new JTextField(20);
		        password = new JPasswordField(20);
		        mail = new JTextField(20);
		        cognome = new JTextField(20);
		        età = new JTextField(20);
		        
		        formPanel.add(usernameLabel);
		        formPanel.add(nome);
		        formPanel.add(surnameLabel);
		        formPanel.add(cognome);
		        formPanel.add(ageLabel);
		        formPanel.add(età);
		        formPanel.add(passwordLabel);
		        formPanel.add(password);
		        formPanel.add(emailLabel);
		        formPanel.add(mail);
		        
		        JButton signupButton = new JButton("Iscriviti");
		        signupButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                
		                String username = nome.getText();
		                String passwordd = String.valueOf(password.getPassword());
		                String email = mail.getText();
		                String surname = cognome.getText();
		                String age = età.getText();
		                
		                int ageint = Integer.parseInt(age);
		                
		                Cliente c=new Cliente(username,surname,email,passwordd,ageint);
		                
		                
		               

		                //Palestra.registraCliente(c);
		                
		                System.out.println("Username: " + username);
		                System.out.println("Password: " + passwordd);
		                System.out.println("Email: " + email);
		                System.out.println("cognome: " + surname);
		                System.out.println("età: " + age); 
		                

		              
		            }
		        });
		        JPanel buttonPanel = new JPanel();
		        buttonPanel.add(signupButton);

		        this.add(formPanel, BorderLayout.CENTER);
		        this.add(buttonPanel, BorderLayout.SOUTH);
		        
			}
			
		}

	}
}
