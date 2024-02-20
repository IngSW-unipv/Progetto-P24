package NextFit;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class reg_button extends JPanel
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
