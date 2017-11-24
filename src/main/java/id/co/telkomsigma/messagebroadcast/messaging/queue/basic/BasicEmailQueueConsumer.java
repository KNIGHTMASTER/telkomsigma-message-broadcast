package id.co.telkomsigma.messagebroadcast.messaging.queue.basic;

import id.co.telkomsigma.messagebroadcast.constant.AppConstant;
import id.co.telkomsigma.messagebroadcast.dto.BasicMailRequestDTO;
import id.co.telkomsigma.messagebroadcast.messaging.IJMSConsumer;
import id.co.telkomsigma.messagebroadcast.service.ICoreMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Component
public class BasicEmailQueueConsumer implements IJMSConsumer<BasicMailRequestDTO> {

    @Autowired
    private ICoreMailSender coreMailSender;

    @JmsListener(destination = AppConstant.Messaging.Queue.EMAIL, containerFactory = AppConstant.Messaging.ConnectionFactory.BASIC_EMAIL)
    @Override
    public void receive(BasicMailRequestDTO p_Message) {
        coreMailSender.sendMailContent(p_Message.getTo(), p_Message.getSubject(), p_Message.getContent());
    }
}
