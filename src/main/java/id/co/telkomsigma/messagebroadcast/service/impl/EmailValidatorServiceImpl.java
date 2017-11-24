package id.co.telkomsigma.messagebroadcast.service.impl;

import id.co.telkomsigma.messagebroadcast.service.IEmailValidatorService;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created on 11/24/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class EmailValidatorServiceImpl implements IEmailValidatorService {

    @Override
    public boolean validateByString(String p_Email) {
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        p_Email = p_Email.trim();
        return p_Email.matches(emailPattern) && p_Email.length() > 0;
    }

    @Override
    public boolean validateByInternet(String p_Email) {
        try {
            InternetAddress internetAddress = new InternetAddress(p_Email);
            internetAddress.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    @Override
    public boolean validateByStringAndInternet(String p_Email) {
        return validateByString(p_Email) && validateByInternet(p_Email);
    }
}
