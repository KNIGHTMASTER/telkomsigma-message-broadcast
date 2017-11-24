package id.co.telkomsigma.messagebroadcast.messaging.queue.verification;

import id.co.telkomsigma.messagebroadcast.constant.AppConstant;
import id.co.telkomsigma.messagebroadcast.dto.EmailVerificationDTO;
import id.co.telkomsigma.messagebroadcast.messaging.IJMSProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class VerificationEmailQueueProducer implements IJMSProducer<EmailVerificationDTO> {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    @Qualifier(AppConstant.Messaging.Queue.EMAIL_VERIFICATION)
    private Queue queueEmailVerification;

    @Override
    public void send(EmailVerificationDTO p_Message) {
        this.jmsMessagingTemplate.convertAndSend(queueEmailVerification, p_Message);
    }
}
