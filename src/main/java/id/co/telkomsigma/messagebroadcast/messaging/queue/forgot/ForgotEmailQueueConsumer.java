package id.co.telkomsigma.messagebroadcast.messaging.queue.forgot;

import id.co.telkomsigma.messagebroadcast.constant.AppConstant;
import id.co.telkomsigma.messagebroadcast.dto.EmailForgotDTO;
import id.co.telkomsigma.messagebroadcast.messaging.IJMSConsumer;
import id.co.telkomsigma.messagebroadcast.service.IForgotMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class ForgotEmailQueueConsumer implements IJMSConsumer<EmailForgotDTO> {

    @Autowired
    private IForgotMailSender forgotMailSender;

    @JmsListener(destination = AppConstant.Messaging.Queue.EMAIL_FORGOT, containerFactory = AppConstant.Messaging.ConnectionFactory.EMAIL_FORGOT)
    @Override
    public void receive(EmailForgotDTO p_Message) {
        forgotMailSender.sendMailWithTemplate(p_Message);
    }
}
