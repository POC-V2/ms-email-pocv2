package com.pocms.email.service;

import com.pocms.email.model.Email;
import com.pocms.email.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

	private final EmailRepository repository;

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.from}")
	private String from;

	@Value("${mail.fake}")
	private boolean mailFake;

	public EmailServiceImpl(EmailRepository repository) {
		this.repository = repository;
	}

	@Override
	public void send(Email email) {
		if (!mailFake) {
			String host = "smtp.mail.yahoo.com";
			Properties properties = System.getProperties();

			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.auth", "true");

			Session session = Session.getInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(
							username,
							password
					);
				}
			});

			session.setDebug(true);
			try {
				MimeMessage message = new MimeMessage(session);

				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmail()));
				message.setSubject(email.getTitulo());
				message.setContent(email.getConteudo(), "text/html");

				LOGGER.info("Enviando e-mail...");
				Transport.send(message);
				repository.save(email);
				LOGGER.info("E-mail enviado com sucesso");
			} catch (MessagingException e) {
				LOGGER.error("Erro ao enviar email", e);
			}
		}
	}

}
