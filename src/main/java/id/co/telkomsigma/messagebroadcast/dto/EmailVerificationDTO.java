package id.co.telkomsigma.messagebroadcast.dto;

import java.io.Serializable;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class EmailVerificationDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -996063525641591223L;

    private String customerName;
    private String obuId;
    private String to;
    private String subject;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getObuId() {
        return obuId;
    }

    public void setObuId(String obuId) {
        this.obuId = obuId;
    }

    @Override
    public String toString() {
        return "EmailVerificationDTO{" +
                "customerName='" + customerName + '\'' +
                ", obuId='" + obuId + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
