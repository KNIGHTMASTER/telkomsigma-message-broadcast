package id.co.telkomsigma.messagebroadcast.constant;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface AppConstant {

    interface Messaging {
        interface TrustedPackages {
            String JAVA_LANG = "java.lang";
            String JAVA_MATH = "java.math";
            String JAVA_UTIL = "java.util";
            String MAIN_APP = "id.co.telkomsigma.messagebroadcast";
        }

        interface ConnectionFactory {
            String BASIC_EMAIL = "jmsBasicEmailListenerContainerFactory";
            String ATTACHED_EMAIL = "jmsAttachedEmailListenerContainerFactory";
            String EMAIL_VERIFICATION = "jmsEmailVerificationListenerContainerFactory";
            String EMAIL_FORGOT = "jmsEmailForgotListenerContainerFactory";
        }

        interface Queue {
            String EMAIL = "email-queue";
            String EMAIL_ATTACHED = "email-queue-attached";
            String EMAIL_VERIFICATION = "email-queue-verification";
            String EMAIL_FORGOT = "email-queue-forgot";
        }
    }
}
