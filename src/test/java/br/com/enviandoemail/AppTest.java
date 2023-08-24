package br.com.enviandoemail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private String userName = "viniciusemailjavaweb@gmail.com";
	private String password = ""; /*REMOVER ANTES DO COMMIT*/
	
	/* Método para testar o email */
	@org.junit.Test
	public void testeEmail() {		
		
		try {
			/* Chamando o properties */
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true"); /* Autorização */
			properties.put("mail.smtp.starttls", "true"); /* Autenticação */
			properties.put("mail.smtp.host", "smtp.gmail.com"); /* Servidor gmail google */
			properties.put("mail.smtp.port", "465"); /* Porta do servidor */
			properties.put("mail.smtp.socketFactory.port", "465"); /* Especifica a porta a ser conectada pelo socket */
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /* Classe socket de conexão ao smtp */
		
			Session session = Session.getInstance(properties, new Authenticator() {
				
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});
			
			Address[] toUser = InternetAddress.parse("visilva569038@gmail.com, viniciusemailjavaweb@gmail.com");
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName)); /*Quem está enviando*/
			message.setRecipients(Message.RecipientType.TO, toUser); /*Email de destino*/
			message.setSubject("Chegou o email do java"); /*Assunto do email*/
			message.setText("Olá estou enviando esse email para testar o email");
			
			Transport.send(message);
			
			System.out.println("============= VOU ENVIAR =============");
			System.out.println(session);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
