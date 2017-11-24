package id.co.telkomsigma.messagebroadcast.service;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @param <CONTENT>
 */
public interface ITemplateMailSender<CONTENT> {

    void sendMailWithTemplate(CONTENT p_Content);
}
