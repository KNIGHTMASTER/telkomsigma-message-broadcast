package id.co.telkomsigma.messagebroadcast.service.builder;

import id.co.telkomsigma.messagebroadcast.dto.EmailVerificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class MailVerificationContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${url-home-app}")
    private String urlHomeApp;

    public String build(EmailVerificationDTO emailVerificationDTO) {
        Context context = new Context();
        context.setVariable("homeApp", urlHomeApp);
        context.setVariable("customerName", emailVerificationDTO.getCustomerName());
        context.setVariable("obuId", emailVerificationDTO.getObuId());
        return templateEngine.process("email-verification", context);
    }
}
