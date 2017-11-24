package id.co.telkomsigma.messagebroadcast.endpoint.impl;

import id.co.telkomsigma.messagebroadcast.dto.BasicMailRequestDTO;
import id.co.telkomsigma.messagebroadcast.dto.EmailForgotDTO;
import id.co.telkomsigma.messagebroadcast.dto.EmailVerificationDTO;
import id.co.telkomsigma.messagebroadcast.dto.response.ResponseData;
import id.co.telkomsigma.messagebroadcast.endpoint.ISimpleMailEndPoint;
import id.co.telkomsigma.messagebroadcast.messaging.queue.attach.AttachedEmailQueueProducer;
import id.co.telkomsigma.messagebroadcast.messaging.queue.basic.BasicEmailQueueProducer;
import id.co.telkomsigma.messagebroadcast.messaging.queue.forgot.ForgotEmailQueueProducer;
import id.co.telkomsigma.messagebroadcast.messaging.queue.verification.VerificationEmailQueueProducer;
import id.co.telkomsigma.messagebroadcast.service.IEmailValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RestController
public class SimpleMailEndPointImpl implements ISimpleMailEndPoint {

    @Autowired
    private BasicEmailQueueProducer basicEmailQueueProducer;

    @Autowired
    private AttachedEmailQueueProducer attachedEmailQueueProducer;

    @Autowired
    private VerificationEmailQueueProducer verificationEmailQueueProducer;

    @Autowired
    private ForgotEmailQueueProducer forgotEmailQueueProducer;

    @Autowired
    private IEmailValidatorService emailValidatorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailEndPointImpl.class);

    @Override
    public ResponseData sendBasicMail(@RequestBody BasicMailRequestDTO p_BasicMailRequestDTO) {
        ResponseData responseData = new ResponseData("X-V01", "Failed Web Service");
        try {
            if (p_BasicMailRequestDTO.getContent() != null && p_BasicMailRequestDTO.getSubject() != null && p_BasicMailRequestDTO.getTo() != null) {
                if (emailValidatorService.validateByStringAndInternet(p_BasicMailRequestDTO.getTo())) {
                    basicEmailQueueProducer.send(p_BasicMailRequestDTO);
                    responseData = new ResponseData("X-VVV", "Success Web Service");
                }
            }
        }catch (Exception e) {
            LOGGER.error(e.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData sendBasicMailWithAttachment(@RequestBody BasicMailRequestDTO p_BasicMailRequestDTO) {
        ResponseData responseData = new ResponseData("X-V01", "Failed Web Service");
        try {
            if (p_BasicMailRequestDTO.getContent() != null && p_BasicMailRequestDTO.getSubject() != null && p_BasicMailRequestDTO.getTo() != null) {
                if (emailValidatorService.validateByStringAndInternet(p_BasicMailRequestDTO.getTo())) {
                    attachedEmailQueueProducer.send(p_BasicMailRequestDTO);
                    responseData = new ResponseData("X-VVV", "Success Web Service");
                }
            }
        }catch (Exception e) {
            LOGGER.error(e.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData sendEmailVerification(@RequestBody EmailVerificationDTO emailVerificationDTO) {
        ResponseData responseData = new ResponseData("X-V01", "Failed Web Service");
        try {
            if (emailVerificationDTO.getSubject() != null && emailVerificationDTO.getTo() != null && emailVerificationDTO.getCustomerName() != null && emailVerificationDTO.getObuId() != null) {
                if (emailValidatorService.validateByStringAndInternet(emailVerificationDTO.getTo())) {
                    verificationEmailQueueProducer.send(emailVerificationDTO);
                    responseData = new ResponseData("X-VVV", "Success Web Service");
                }
            }
        }catch (Exception e) {
            LOGGER.error(e.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData sendEmailForgot(@RequestBody EmailForgotDTO p_EmailForgotDTO) {
        ResponseData responseData = new ResponseData("X-V01", "Failed Web Service");
        try {
            if (p_EmailForgotDTO.getTo() != null && p_EmailForgotDTO.getSubject() != null && p_EmailForgotDTO.getToken() != null) {
                if (emailValidatorService.validateByStringAndInternet(p_EmailForgotDTO.getTo())) {
                    forgotEmailQueueProducer.send(p_EmailForgotDTO);
                    responseData = new ResponseData("X-VVV", "Success Web Service");
                }
            }
        }catch (Exception e) {
            LOGGER.error(e.toString());
        }
        return responseData;
    }
}
