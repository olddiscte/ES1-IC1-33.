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

import twitter4j.Twitter;
import twitter4j.TwitterException;

import programs.*;
public class InterfaceTwitter {

	private JFrame frame;
	private TwitterProg twitter;
	
	public InterfaceTwitter() {
		frame = new JFrame("Twitter");

		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());

		addFrameContent();

		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel

		frame.setSize(400, 200);
		
		
		
		twitter = new TwitterProg();
		

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
		JButton twettar = new JButton("Tweetar"); twettar.setBorderPainted(true);
		JButton procurar = new JButton("Procurar"); procurar.setBorderPainted(true);
		
		twettar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String code =  JOptionPane.showInputDialog(frame, "O que queres Twettar?");
			
				
				try {				
					twitter.tweetar( code);
				} catch (TwitterException e1) {
					e1.printStackTrace();
				} 
			}
		});
		
		procurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String code =  JOptionPane.showInputDialog(frame, "Queres procurar Tweets de quem?");
				
				try {				
					twitter.procurar(code);
				} catch (TwitterException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		
		frame.add(twettar);
		frame.add(procurar);
	}
	
	
}
