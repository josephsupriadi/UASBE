package tas.co.id.tas_be.controller;

import lombok.AllArgsConstructor;
import tas.co.id.tas_be.model.dto.request.EmailRequest;
import tas.co.id.tas_be.service.EmailService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

  private EmailService emailService;

  @PostMapping
  public EmailRequest simpleMail(@RequestBody EmailRequest emailRequest) {
    return emailService.simpleEmail(emailRequest);
  }

  @PostMapping("/attach")
  public EmailRequest sendMessageWithAttachment(
    @RequestBody EmailRequest emailRequest
  ) {
    return emailService.sendMessageWithAttachment(emailRequest);
  }
}
