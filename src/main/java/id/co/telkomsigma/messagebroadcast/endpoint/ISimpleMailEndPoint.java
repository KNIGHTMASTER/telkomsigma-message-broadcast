package id.co.telkomsigma.messagebroadcast.endpoint;

import id.co.telkomsigma.messagebroadcast.dto.BasicMailRequestDTO;
import id.co.telkomsigma.messagebroadcast.dto.EmailForgotDTO;
import id.co.telkomsigma.messagebroadcast.dto.EmailVerificationDTO;
import id.co.telkomsigma.messagebroadcast.dto.response.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@RequestMapping("/simplemail")
public interface ISimpleMailEndPoint {

    @ResponseBody
    @PostMapping("/basic")
    ResponseData sendBasicMail(@RequestBody BasicMailRequestDTO p_BasicMailRequestDTO);

    @ResponseBody
    @PostMapping("/basicattachment")
    ResponseData sendBasicMailWithAttachment(@RequestBody BasicMailRequestDTO p_BasicMailRequestDTO);

    @ResponseBody
    @PostMapping("/emailverification")
    ResponseData sendEmailVerification(@RequestBody EmailVerificationDTO p_TemplateMailRequestDTO);

    @ResponseBody
    @PostMapping("/emailforgot")
    ResponseData sendEmailForgot(@RequestBody EmailForgotDTO p_EmailForgotDTO);
}
