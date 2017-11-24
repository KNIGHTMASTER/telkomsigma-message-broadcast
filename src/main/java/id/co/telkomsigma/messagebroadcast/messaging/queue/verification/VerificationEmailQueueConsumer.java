package id.co.telkomsigma.messagebroadcast.messaging.queue.verification;

import id.co.telkomsigma.messagebroadcast.constant.AppConstant;
import id.co.telkomsigma.messagebroadcast.dto.EmailVerificationDTO;
import id.co.telkomsigma.messagebroadcast.messaging.IJMSConsumer;
import id.co.telkomsigma.messagebroadcast.service.IVerificationMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class VerificationEmailQueueConsumer implements IJMSConsumer<EmailVerificationDTO> {

    @Autowired
    private IVerificationMailSender verificationMailSender;

    @JmsListener(destination = AppConstant.Messaging.Queue.EMAIL_VERIFICATION, containerFactory = AppConstant.Messaging.ConnectionFactory.EMAIL_VERIFICATION)
    @Override
    public void receive(EmailVerificationDTO p_Message) {
        verificationMailSender.sendMailWithTemplate(p_Message);
    }
}
