package br.com.enviandoemail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ObjetoEnviaEmail {

	private String userName = "viniciusemailjavaweb@gmail.com";
	private String password = ""; /* REMOVER ANTES DO COMMIT */
	
	private String listaDestinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	public ObjetoEnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail(boolean envioHtml) throws Exception {
		/* Chamando o properties */
		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*"); /* Segurança ssl */
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

		Address[] toUser = InternetAddress.parse(listaDestinatarios);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente)); /* Quem está enviando */
		message.setRecipients(Message.RecipientType.TO, toUser); /* Email de destino */
		message.setSubject(assuntoEmail); /* Assunto do email */
		
		if (envioHtml) {
			message.setContent(textoEmail, "text/html; charset=utf-8");
		} else {			
			message.setText(textoEmail);
		}
		
		Transport.send(message);
	}
	
	public void enviarEmailAnexo(boolean envioHtml) throws Exception {
		/* Chamando o properties */
		Properties properties = new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*"); /* Segurança ssl */
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
		
		Address[] toUser = InternetAddress.parse(listaDestinatarios);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName, nomeRemetente)); /* Quem está enviando */
		message.setRecipients(Message.RecipientType.TO, toUser); /* Email de destino */
		message.setSubject(assuntoEmail); /* Assunto do email */
		
		/*Primeira parte do email que é o texto e a descrição do email*/
		MimeBodyPart corpoEmail = new MimeBodyPart();
		
		if (envioHtml) {
			corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
		} else {			
			corpoEmail.setText(textoEmail);
		}
		
		/*Segunda parte do email que são os anexos em PDF ou qq outra extensão*/
		MimeBodyPart anexoEmail = new MimeBodyPart();
		
		/*onde é passado o simulador de pdf pode ser add o arquivo gravado no banco*/
		anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(simuladordePDF(), "application/pdf")));
		anexoEmail.setFileName("anexoemail.pdf");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpoEmail);
		multipart.addBodyPart(anexoEmail);
		
		message.setContent(multipart);		
		
		Transport.send(message);
	}
	
	
	/**
	 * O método abaixo simula o PDF de qualquer arquivo que possa ser enviado por anexo no email
	 * você pode pegar o arquivo no banco, em b64, byte[], stream de arquivos
	 * 
	 * @return pdf em branco com o texto do paragrafo de exemploz
	 * */
	private FileInputStream simuladordePDF() throws Exception {
		/*Dando entrada no arquivo*/
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		/*Escrevendo o pdf*/
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com java mail, texto do pdf"));
		document.close();
		
		return new FileInputStream(file);
	}

}
