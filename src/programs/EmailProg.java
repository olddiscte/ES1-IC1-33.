package programs;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailProg {

	
	/*
	public static void main(String[] args) {
		
		try {
		Properties prop = new Properties();
		//prop.setProperty("mail.store.protocol", "imaps");
		prop.put("mail.store.host", "smtp.gmail.com");
		Session emailSession = Session.getDefaultInstance(prop);
		Store emailStore = emailSession.getStore("imaps");
		emailStore.connect("imap.gmail.com", "pedromasantos96@gmail.com", "anjoanpe10");
		//getting inbox folder
		
		Folder emailFolder = emailStore.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
		Message messages[] = emailFolder.getMessages();
		
		for(int i = messages.length-3; i< messages.length;i++) {
			Message message = messages[i];
			System.out.println("Email Number:" +i+1);
			System.out.println("Subject:" + message.getSubject());
			System.out.println("From:" + message.getFrom()[0]);
			System.out.println("Sent date: " + message.getSentDate());		
		}
		
		
		//closing emailFolder and emailStore
		emailFolder.close(false);
		emailStore.close();
		
		
		}
		catch(NoSuchProviderException e) {
			e.printStackTrace();
		}
		catch(MessagingException me) {
			me.printStackTrace();
		}
	} 
	
	public static void main(String[] args) {
	      //Declare recipient's & sender's e-mail id.
	      String destmailid = "pedromas_96@hotmail.com";
	      String sendrmailid = "pedromasantos96@gmail.com";   
	     //Mention user name and password as per your configuration
	      final String uname = "pedromasantos96@gmail.com";
	      final String pwd = "anjoanpe10";
	      //We are using relay.jangosmtp.net for sending emails
	      String smtphost = "Smtp.gmail.com";
	     //Set properties and their values
	      Properties propvls = new Properties();
	      propvls.put("mail.smtp.auth", "true");
	      propvls.put("mail.smtp.starttls.enable", "true");
	      propvls.put("mail.smtp.host", smtphost);
	      propvls.put("mail.smtp.port", "465");
	      //Create a Session object & authenticate uid and pwd
	      Session sessionobj = Session.getInstance(propvls,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(uname, pwd);
	    }
	         });
	 
	      try {
	    //Create MimeMessage object & set values
	    Message messageobj = new MimeMessage(sessionobj);
	    messageobj.setFrom(new InternetAddress(sendrmailid));
	    messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destmailid));
	    messageobj.setSubject("This is test Subject");
	    messageobj.setText("Checking sending emails by using JavaMail APIs");
	   //Now send the message
	    Transport.send(messageobj);
	    System.out.println("Your email sent successfully....");
	      } catch (MessagingException exp) {
	         throw new RuntimeException(exp);
	      }
	   } */
	
	public static void main(String[] args) {
		String  d_email = "address@gmail.com",
	            d_uname = "Name",
	            d_password = "urpassword",
	            d_host = "smtp.gmail.com",
	            d_port  = "465",
	            m_to = "toAddress@gmail.com",
	            m_subject = "Indoors Readable File: ",
	            m_text = "This message is from Indoor Positioning App. Required file(s) are attached.";
	    Properties props = new Properties();
	    props.put("mail.smtp.user", d_email);
	    props.put("mail.smtp.host", d_host);
	    props.put("mail.smtp.port", d_port);
	    props.put("mail.smtp.starttls.enable","true");
	    props.put("mail.smtp.debug", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.socketFactory.port", d_port);
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");

	    SMTPAuthenticator auth = new SMTPAuthenticator();
	    Session session = Session.getInstance(props, auth);
	    session.setDebug(true);

	    MimeMessage msg = new MimeMessage(session);
	    try {
	        msg.setSubject(m_subject);
	        msg.setFrom(new InternetAddress(d_email));
	        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));

	Transport transport = session.getTransport("smtps");
	            transport.connect(d_host, Integer.valueOf(d_port), d_uname, d_password);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();

	        } catch (AddressException e) {
	            e.printStackTrace();
	            return false;
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	
	
}
