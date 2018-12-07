package programs;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class EmailProg {

	private String user;
	private String password;
	private Store store;
	
	public EmailProg(String user, String password) throws MessagingException, IOException {
		this.user = user; this.password = password;
		this.store = login();
		lerInbox();
	}
	
	
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
	    	  return store;
	      }
	      System.out.println("Erro ao fazer Login");
		  return null;    
	}
	
	private void lerInbox() throws MessagingException, IOException {
		Folder emailFolder = store.getFolder("INBOX");
	      emailFolder.open(Folder.READ_ONLY);

	      // retrieve the messages from the folder in an array and print it
	      Message[] messages = emailFolder.getMessages();
	      System.out.println("messages.length---" + messages.length);
	      
	      for (int i = 0; i < (messages.length + 2) - messages.length; i++) {
	         Message message = messages[i];
	         if(message.getFrom()[0].toString().contains("iscte-iul.pt")) {
	        	 System.out.println("---------------------------------");
		         System.out.println("Email Number " + (i + 1));
		         System.out.println("Subject: " + message.getSubject());
		         System.out.println("From: " + message.getFrom()[0]);
		         System.out.println("Text: " + message.getContent().toString());
	         }
	         

	      }

	      //close the store and folder objects
	      emailFolder.close(false);
	}
	
	
	
	public static void main(String[] args) throws MessagingException, IOException {
		EmailProg x = new EmailProg("pmass@iscte-iul.pt", "Aanjoanpe10");
	}
}
