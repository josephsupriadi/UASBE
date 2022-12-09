package tas.co.id.tas_be.service;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import tas.co.id.tas_be.model.dto.request.EmailRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

  private JavaMailSender mailSender;

  public EmailRequest simpleEmail(EmailRequest emailRequest) {
   SimpleMailMessage message = new SimpleMailMessage();
   message.setTo(emailRequest.getTo());
   message.setSubject(emailRequest.getSubject());
   message.setText(emailRequest.getBody());
   
   mailSender.send(message);
   return emailRequest;
  }

  public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

      helper.setTo(emailRequest.getTo());
      helper.setSubject(emailRequest.getSubject());
      helper.setText(emailRequest.getBody());

      FileSystemResource fileSystemResource = new FileSystemResource(
        new File(emailRequest.getAttach())
      );
      helper.addAttachment(
        fileSystemResource.getFilename(),
        fileSystemResource
      );

      mailSender.send(mimeMessage);
      System.out.println("Send success...");
    } catch (MessagingException e) {
      System.out.println(e);
      throw new IllegalStateException("Failed to send email...");
    }
    return emailRequest;
  }
}
