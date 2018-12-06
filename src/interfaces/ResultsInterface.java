package interfaces;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ResultsInterface {

	
	private JFrame frame;
	private InterfaceTwitter iTwitter;
	private InterfaceFacebook iFacebook;

	public ResultsInterface(String results) {
		frame = new JFrame("Results");

		// para que o botao de fechar a janela termine a aplicacao
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// conteudo em sequencia da esquerda para a direita
		frame.setLayout(new FlowLayout());

		addFrameContent(results);
	

		// para que a janela se redimensione de forma a ter todo o seu conteudo visivel

		frame.setSize(400, 200);

	}

	public void open() {
		// para abrir a janela (torna-la visivel)
		frame.setVisible(true);
	}

	private void addFrameContent(String results) {


		/* para organizar o conteudo em grelha (linhas x colunas)
			se um dos valores for zero, o numero de linhas ou colunas (respetivamente) fica indefinido,
			e estas sao acrescentadas automaticament */
		frame.setLayout(new GridLayout(1,0));

		JTextArea result = new JTextArea(results);
		result.setLineWrap(true);
		result.setEditable(false);
		
		frame.add(result);
		

	}
	

}
