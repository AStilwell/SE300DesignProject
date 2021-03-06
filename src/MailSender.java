import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Peter Devyatkin
 * 
 * Embry-Riddle Aeronautical University
 * 
 * SE-300 Project
 * 
 * contact info: peterdevyatkin@gmail.com
 *
 */
public class MailSender
{
	/**
	 * This class has one method that allows for emails to be sent from java.
	 * 
	 * @param sender - string - email address of sender
	 * @param senderPass - string - password of sender
	 * @param to - array of strings - address of recipient(s), 
	 * do not pass an array using this if you want to send individual emails to each recipient
	 * instead use a loop to call this function for each recipient (FOR NOW, WILL SEE IF WORKAROUND THAT IS SIMPLER)
	 * @param message - string - content of email
	 * @param subject - string - subject line of email
	 * @return will give true on success, false on failure
	 * 
	 */
	public static void sendEmail(String sender, String senderPass, String to[], String message, String subject) throws MessagingException
	{
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", sender);
		properties.put("mail.smtp.password", senderPass);
		properties.put("mail.smtp.port", 587); // This port works on EagleNet, inquire about others that work!
		properties.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(properties, null);
		MimeMessage mimeMessage = new MimeMessage(session);
		
		//try
		//{
			mimeMessage.setFrom(new InternetAddress(sender));
			
			InternetAddress[] toAddress = new InternetAddress[to.length];
			
			for(int i = 0; i < to.length; i++)
			{
				toAddress[i] = new InternetAddress(to[i]);
			}
			for(int i = 0; i < toAddress.length; i++)
			{
				mimeMessage.addRecipient(RecipientType.TO, toAddress[i]);
			}
			
			mimeMessage.setSubject(subject); // Subject line
			mimeMessage.setText(message + "\n\nClick the link below and change the 0 to a 1 next to your name:\n\nhttps://docs.google.com/spreadsheets/d/1o3k5Koml7QabFHP1w6ou4-UShjua2XPqjNvSJWhQSs4/edit?usp=sharing"); // Content of message
			Transport transport = session.getTransport("smtp");
			transport.connect(host, sender, senderPass); // Sender authentication
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close(); // Terminates transport process
			System.out.println("Send Successful");
			//return true; // Method should end here if everything works.
		//}
		//catch(MessagingException me)
		//{
			//me.printStackTrace(); // This should happen if the user enters invalid credentials.
		//}
		
		//return false; // Returns false if for any reason the code above doesn't run.
	}
}
