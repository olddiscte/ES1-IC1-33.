package interfaces;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import programs.EmailProg;

/**
 * 
 * @author Pedro Santos/Pedro Brites
 *
 */
public class InterfaceLoginEmail {


	private JFrame frame;
	private EmailProg email;

	/**
	 * Construtor
	 */
	public InterfaceLoginEmail() {
		frame = new JFrame("Email");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		addFrameContent();
		frame.setSize(400, 200);
		frame.pack();
		//face = new FacebookProg();
	}

	private void addFrameContent() {

		frame.setLayout(new GridLayout(1,0));
		JPasswordField pw = new JPasswordField();
		JTextField login = new JTextField("email");
		JButton log = new JButton("Login");


		log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					email = new EmailProg(login.getText(), String.valueOf(pw.getPassword()));
				} catch (MessagingException | IOException e1) {

					e1.printStackTrace();
				}
			}
		});

		frame.add(login);
		frame.add(pw);
		frame.add(log);


	}

	public void open() {
		frame.setVisible(true);
	}
}
