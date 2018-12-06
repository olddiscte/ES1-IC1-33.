package interfaces;
import programs.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class Interface{
	private JFrame frame;
	private InterfaceTwitter iTwitter;
	private InterfaceFacebook iFacebook;

	public Interface() {
		frame = new JFrame("AppSearcher");

		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());

		addFrameContent();

		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel

		frame.setSize(400, 200);

	}

	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
	}

	private void addFrameContent() {


		/* para organizar o conteudo em grelha (linhas x colunas)
			se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
			e estas sao acrescentadas automaticamente */
		frame.setLayout(new GridLayout(1,0));

		//JCheckBox facebook = new JCheckBox("Facebook"); facebook.setBorderPainted(true);
		JButton facebook = new JButton("Facebook"); facebook.setBorderPainted(true);
		JButton twitter = new JButton("Twitter");   	twitter.setBorderPainted(true);
		JButton email = new JButton("Email"); email.setBorderPainted(true);



		frame.add(twitter);
		frame.add(email);
		frame.add(facebook);

		twitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iTwitter = new InterfaceTwitter();
				iTwitter.open();
			}
		});



		facebook.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				iFacebook = new InterfaceFacebook();
				iFacebook.open();
			}
		});

	}

	public static void main(String[] args) {
		Interface window = new Interface();
		window.open();
	}
}

