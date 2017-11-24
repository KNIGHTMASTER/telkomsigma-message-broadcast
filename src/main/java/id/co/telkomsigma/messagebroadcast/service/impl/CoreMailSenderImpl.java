package id.co.telkomsigma.messagebroadcast.service.impl;

import id.co.telkomsigma.messagebroadcast.service.ICoreMailSender;
import id.co.telkomsigma.messagebroadcast.service.builder.MailTemplateContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class CoreMailSenderImpl implements ICoreMailSender {

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreMailSenderImpl.class);

    @Value("${spring.mail.username}")
    private String userName;

    @Autowired
    private MailTemplateContentBuilder mailTemplateContentBuilder;

    private MimeMessage message;

    @PostConstruct
    public void init() {
        message = mailSender.createMimeMessage();
    }

    @Override
    public void sendMailContent(String to, String subject, String content) {
        buildBasicMimeMessageHelper(message, to, subject, content);
        mailSender.send(message);
    }

    @Override
    public void sendMailWithAttachment(String p_To, String p_Subject, String p_TextContent, String[] p_AttachmentFilePath) {
        buildAttachmentMimeMessageHelper(message, p_To, p_Subject, p_TextContent, p_AttachmentFilePath);
        mailSender.send(message);
    }

    @Override
    public void sendMailWithAttachmentAndInlineResource(String p_From, String p_To, String p_Subject, String p_TextContent, File p_Attachment, String p_AttachmentName, String p_InlineResource) {

    }

    @Override
    public void sendMailWithTemplate(String p_To, String p_Subject, String p_Content) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            String content = mailTemplateContentBuilder.build("Example Content");
            messageHelper.setFrom(userName);
            messageHelper.setTo(p_To);
            messageHelper.setSubject(p_Subject);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(mimeMessagePreparator);
        } catch (MailException e) {
            LOGGER.error("Error sending Email with Template ".concat(e.toString()));
        }
    }

    private MimeMessageHelper buildBasicMimeMessageHelper(MimeMessage mimeMessage, String to, String subject, String content){
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            InternetAddress addressFrom = new InternetAddress("gmail.com", userName);
            helper.setFrom(addressFrom);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setPriority(1);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return helper;
    }

    private MimeMessageHelper buildAttachmentMimeMessageHelper(MimeMessage mimeMessage, String to, String subject, String content, String[] p_PathFile) {
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            InternetAddress addressFrom= new InternetAddress("gmail.com", userName);

            helper.setFrom(addressFrom);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setPriority(1);

            if (p_PathFile.length > 0) {
                for (String attachmentPath : p_PathFile) {
                    FileSystemResource fileSystemResource = new FileSystemResource(attachmentPath);
                    helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
                }
            }
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return helper;
    }
}
