package programs;

import java.security.NoSuchProviderException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;



public class teste {

	
	public static void check(String user,
		      String password) 
		   {
		      try {

		      //create properties field
		      Properties properties = new Properties();

		      properties.put("mail.pop3.host", "outlook.office365.com");
		      properties.put("mail.pop3.port", "995");
		      properties.put("mail.pop3s.ssl.trust","*");
		      Session emailSession = Session.getInstance(properties);
		  
		      //create the POP3 store object and connect with the pop server
		      Store store = emailSession.getStore("pop3s");

		      store.connect("outlook.office365.com", user, password);

		      //create the folder object and open it
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
		      store.close();

		      } catch (MessagingException e) {
		         e.printStackTrace();
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }

		   public static void main(String[] args) {

		      String host = "pop.gmail.com";// change accordingly
		      String mailStoreType = "pop3";
		      String username = "pmass@iscte-iul.pt";// change accordingly
		      String password = "Aanjoanpe10";// change accordingly

		      check(username, password);

		   }
}
