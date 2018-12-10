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
 * @author Pedro Santos/Pedro Brites
 *
 */
public class TextEmailSenderInterface {

	private JFrame frame;
	private EmailProg email;

	/**
	 * Construtor
	 * @param email
	 */
	public TextEmailSenderInterface(EmailProg email) {
		this.email = email;
		frame = new JFrame("Email");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		addFrameContent();
		frame.setSize(400, 200);
		frame.pack();
	}

	private void addFrameContent() {
		frame.setLayout(new GridLayout(1,0));

		JTextField txt = new JTextField("Escreve aqui");
		JButton send = new JButton("Enviar");


		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String x = txt.getText();
					email.enviarEmail(x);
				} catch (MessagingException e1) {

					e1.printStackTrace();
				}
			}
		});

		frame.add(txt);
		frame.add(send);
	}

	public void open() {
		frame.setVisible(true);
	}
}
