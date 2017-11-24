package id.co.telkomsigma.messagebroadcast.service;

import java.io.File;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 *
 */
public interface ICoreMailSender{

    void sendMailContent(String to, String subject, String content);

    void sendMailWithAttachment(String p_To, String p_Subject, String p_TextContent, String[] p_AttachmentFilePath);

    void sendMailWithTemplate(String p_To, String p_Subject, String p_Content);

    void sendMailWithAttachmentAndInlineResource(String p_From, String p_To, String p_Subject, String p_TextContent, File p_Attachment, String p_AttachmentName, String p_InlineResource);
}
