package com.running.app.services;

import com.running.app.exceptions.MikeRunningException;
import com.running.app.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

  private final JavaMailSender mailSender;
  private final MailContentBuilder mailContentBuilder;

  @Async
  public void sendMail(NotificationEmail notificationEmail) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("MikeRunning@gmail.com");
      messageHelper.setTo(notificationEmail.getRecipient());
      messageHelper.setSubject(notificationEmail.getSubject());
      messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
    };
    try {
      mailSender.send(messagePreparator);
      log.info("Activation Email Sent!");
    } catch (MailException exception) {
      throw new MikeRunningException("Exception occured when sending mail to " + notificationEmail.getRecipient());
    }
  }
}
