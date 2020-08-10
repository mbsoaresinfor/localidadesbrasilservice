package br.com.mbs.localidadesbrasilservice.service;


import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

import br.com.mbs.localidadesbrasilservice.vo.Email;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger LOG = Logger.getLogger(EmailServiceImpl.class);
	
	@Value("${email.enviar.habilitado}")
	private String enviarEmailHabilitado;
	
	@Value("${email.de}")
	private String de;
	
	@Value("${email.host}")
	private String host;
	
	@Value("${email.port}")
	private String port;
	
	@Value("${email.usuario}")
	private String usuario;
	
	@Value("${email.senha}")
	private String senha;
	
	
	@Override
	public void envia(Email email) {
		try {
			if(Boolean.valueOf(enviarEmailHabilitado)) {			
				enviaEmail(email);
			}else {
				LOG.info("ENVIO DE EMAIL DESABILITADO");
				LOG.info("SERIA ENVIADO EMAIL PARA: " + email.getPara());				
			}
			
		}catch(Exception e) {
			LOG.error(e);
		}
		
		
	}

	private void enviaEmail(Email email) throws MessagingException, AddressException {
		LOG.info("Enviando email para: " + email.getPara());
		Properties props = System.getProperties();
	     
		props.put("mail.smtp.host", host);
	    props.put("mail.smtp.socketFactory.port", port);
	    props.put("mail.smtp.socketFactory.class",
	    "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", port);

	    Session session = Session.getDefaultInstance(props,
	    	      new javax.mail.Authenticator() {
	    	           protected PasswordAuthentication getPasswordAuthentication()
	    	           {
	    	                 return new PasswordAuthentication(usuario,
	    	                 senha);
	    	           }
	    	      });
	    
	  
	     Message msg = new MimeMessage(session);
		
	     msg.setFrom(new InternetAddress(de));

         msg.setRecipients(Message.RecipientType.TO,
                 InternetAddress.parse(email.getPara()));

         msg.setSubject(email.getTitulo());
         
         MimeBodyPart body = new MimeBodyPart();
         body.setContent("Content-type","text");
         body.addHeader("Content-type", "text");
         body.setText(email.getCorpo());
         
         byte[] bytes = email.getAnexo();
       
         DataSource dataSource = new ByteArrayDataSource(bytes, email.getMimeTypeAnexo());
         //"application/pdf
         MimeBodyPart pdfBodyPart = new MimeBodyPart();
         pdfBodyPart.setDataHandler(new DataHandler(dataSource));
         pdfBodyPart.setFileName(email.getNomeAnexo());
        
         Multipart mp = new MimeMultipart();
         mp.addBodyPart(body);
         mp.addBodyPart(pdfBodyPart);

         msg.setContent(mp);


         Transport.send(msg);
         

         
	}

}
