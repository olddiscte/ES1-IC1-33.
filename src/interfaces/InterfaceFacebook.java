package interfaces;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import programs.FacebookProg;
import programs.TwitterProg;
import twitter4j.TwitterException;
public class InterfaceFacebook {
	
	private JFrame frame;
	private FacebookProg face;
	
	public InterfaceFacebook() {
		frame = new JFrame("Facebook");

		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());

		addFrameContent();

		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel

		frame.setSize(400, 200);



		face = new FacebookProg();


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

		JButton perfil = new JButton("User Posts"); perfil.setBorderPainted(true);

		perfil.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				face.searchUserFeed();; 
			}
		});

		frame.add(perfil);
	}
}
