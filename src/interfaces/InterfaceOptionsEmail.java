package interfaces;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import programs.EmailProg;


/**
 * @author Pedro Santos/Pedro Brites
 *
 */
public class InterfaceOptionsEmail {


	private JFrame frame;
	private EmailProg email;

	/**
	 * Construtor
	 * @param email
	 */
	public InterfaceOptionsEmail(EmailProg email) {
		this.email = email;
		frame = new JFrame("Email");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		addFrameContent();
		frame.setSize(400, 200);
		frame.pack();
	}


	private void addFrameContent() {
		JButton lerInbox = new JButton("Ler Inbox");
		JButton enviarMail = new JButton("Enviar Mail");


		lerInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					email.lerInbox();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		enviarMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TextEmailSenderInterface(email).open();


			}
		});

		frame.add(enviarMail);
		frame.add(lerInbox);
	}

	public void open() {
		frame.setVisible(true);
	}
}
