package id.co.telkomsigma.messagebroadcast.service.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class MailTemplateContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    public String build(String p_Message) {
        Context context = new Context();
        context.setVariable("message", p_Message);
        return templateEngine.process("email-template", context);
    }
}
