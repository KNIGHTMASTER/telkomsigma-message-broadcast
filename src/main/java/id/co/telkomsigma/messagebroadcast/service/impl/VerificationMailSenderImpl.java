package id.co.telkomsigma.messagebroadcast.service.impl;

import id.co.telkomsigma.messagebroadcast.dto.EmailVerificationDTO;
import id.co.telkomsigma.messagebroadcast.service.IVerificationMailSender;
import id.co.telkomsigma.messagebroadcast.service.builder.MailVerificationContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class VerificationMailSenderImpl implements IVerificationMailSender {
    @Autowired
    private JavaMailSender mailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreMailSenderImpl.class);

    @Value("${spring.mail.username}")
    private String userName;

    @Autowired
    private MailVerificationContentBuilder mailVerificationContentBuilder;

    @Override
    public void sendMailWithTemplate(EmailVerificationDTO p_Content) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            String content = mailVerificationContentBuilder.build(p_Content);
            messageHelper.setFrom(userName);
            messageHelper.setTo(p_Content.getTo());
            messageHelper.setSubject(p_Content.getSubject());
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(mimeMessagePreparator);
        } catch (MailException e) {
            LOGGER.error("Error sending Email with Template ".concat(e.toString()));
        }
    }
}
