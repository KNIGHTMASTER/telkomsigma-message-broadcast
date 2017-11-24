package id.co.telkomsigma.messagebroadcast.messaging;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IJMSConsumer<MESSAGE> {
    void receive(MESSAGE p_Message);
}