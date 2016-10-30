import java.util.Properties;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Peter Devyatkin
 *
 */
public class MailSender
{
	/**
	 * @param sender - string - email address of sender
	 * @param senderPass - string - password of sender
	 * @param to - array of strings - address of recipient(s), 
	 * do not pass an array using this if you want to send individual emails to each recipient
	 * instead use a loop to call this function for each recipient (FOR NOW, WILL SEE IF WORKAROUND THAT IS SIMPLER)
	 * @param message - string - content of email
	 * @param subject - string - subject line of email
	 * @return will give true on success, false on failure
	 */
	public static boolean sendEmail(String sender, String senderPass, String to[], String message, String subject)
	{
		String host = "smtp.gmail.com";
		
		Properties properties = System.getProperties();
		
		properties.put("mail.smtpy.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", sender);
		properties.put("mail.smtp.password", senderPass);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", true);
		
		Session session = Session.getDefaultInstance(properties, null);
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try
		{
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
			
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);
			Transport transport = session.getTransport("smpt");
			transport.connect(host, sender, senderPass);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			return true;
		}
		catch(MessagingException me)
		{
			me.printStackTrace();
		}
		
		return false;
	}
}
