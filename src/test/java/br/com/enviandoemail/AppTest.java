package br.com.enviandoemail;

import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	/*Método para testar o email*/
	@org.junit.Test
	public void testeEmail() {
		
		/*Chamando o properties*/
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true"); /*Autorização*/
		properties.put("mail.smtp.starttls", "true"); /*Autenticação*/
		properties.put("mail.smtp.host", "smtp.gmail.com"); /*Servidor gmail google*/
		properties.put("mail.smtp.port", "465"); /*Porta do servidor*/
		properties.put("mail.smtp.socketFactory.port", "465"); /*Especifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /*Classe socket de conexão ao smtp*/
		
	}

}
