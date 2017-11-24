package id.co.telkomsigma.messagebroadcast.service;

/**
 * Created on 11/24/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IEmailValidatorService {

    boolean validateByString(String p_Email);

    boolean validateByInternet(String p_Email);

    boolean validateByStringAndInternet(String p_Email);
}
