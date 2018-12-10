package programs;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import interfaces.InterfaceOptionsEmail;
import interfaces.ResultsInterface;

/**
 * @author Pedro Santos/Pedro Brites
 *
 */

public class EmailProg {

	private String user;
	private String password;
	private Store store;
	private Session sess;
	private ResultsInterface results;

	/**
	 * Construtor
	 * @param user
	 * @param password
	 * @throws MessagingException
	 * @throws IOException
	 */
	public EmailProg(String user, String password) throws MessagingException, IOException {
		this.user = user; this.password = password;
		if( !(login() == null)) {
			new InterfaceOptionsEmail(this).open();
		}
	}

	/**
	 * Inicializa a store e Session, faz "login" 
	 * @return Store/null
	 * @throws MessagingException
	 */
	private Store login() throws MessagingException {
		//create properties field
		Properties properties = new Properties();

		properties.put("mail.pop3.host", "outlook.office365.com");
		properties.put("mail.pop3.port", "995");
		properties.put("mail.pop3s.ssl.trust","*");
		Session emailSession = Session.getInstance(properties); 
		Store store = emailSession.getStore("pop3s");

		store.connect("outlook.office365.com", user, password);
		if(store.isConnected()) {
			System.out.println("Login Com Sucesso");
			this.store = store;
			this.sess = emailSession;
			return store;
		}
		System.out.println("Erro ao fazer Login");
		return null;    
	}

	/**
	 * Lê a inbox do email associado
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void lerInbox() throws MessagingException, IOException {
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);

		// retrieve the messages from the folder in an array and print it
		Message[] messages = emailFolder.getMessages();

		StringBuilder str = new StringBuilder();
		str.append("Conteúdo dos últimos 10 emails \n");

		for (int i = 0; i < (messages.length + 10) - messages.length; i++) {
			Message message = messages[i];
			if(message.getFrom()[0].toString().contains("iscte-iul.pt")) {
				str.append("---------------------------------\n");
				str.append("Email Número: " + (i + 1) + "\n");
				str.append("Conteúdo: " + message.getSubject() + "\n");
				str.append("De: " + message.getFrom()[0] + "\n");
				str.append("Texto: " + message.getContent().toString() + "\n");
			}
		}

		new ResultsInterface(str.toString()).open();;
		//close the store and folder objects
		emailFolder.close(false);
	}

	/**
	 * Envia um email para destinatario desejado, com o conteudo passado como argumento
	 * @param text
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void enviarEmail(String text) throws AddressException, MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});


		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("pmass@iscte-iul.pt"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("pedromas_96@hotmail.com"));
			message.setSubject("Trabalho de ES1");
			message.setText(text);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
