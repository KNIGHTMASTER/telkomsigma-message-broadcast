package id.co.telkomsigma.messagebroadcast.service.impl;

import id.co.telkomsigma.messagebroadcast.dto.EmailForgotDTO;
import id.co.telkomsigma.messagebroadcast.service.IForgotMailSender;
import id.co.telkomsigma.messagebroadcast.service.builder.MailForgotContentBuilder;
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
public class ForgotMailSenderImpl implements IForgotMailSender {
    @Autowired
    private JavaMailSender mailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreMailSenderImpl.class);

    @Value("${spring.mail.username}")
    private String userName;

    @Autowired
    private MailForgotContentBuilder mailForgotContentBuilder;

    @Override
    public void sendMailWithTemplate(EmailForgotDTO p_Content) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            String content = mailForgotContentBuilder.build(p_Content.getToken());
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
