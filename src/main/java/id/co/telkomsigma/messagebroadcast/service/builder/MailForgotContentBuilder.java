package id.co.telkomsigma.messagebroadcast.service.builder;

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
public class MailForgotContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${url-forgot-password}")
    private String urlForgotPassword;

    @Value("${url-home-app}")
    private String urlHomeApp;

    public String build(String p_Token) {
        Context context = new Context();
        context.setVariable("urlForgotPassword", urlForgotPassword.concat("&token=").concat(p_Token));
        context.setVariable("homeApp", urlHomeApp);
        return templateEngine.process("email-forgot", context);
    }
}
