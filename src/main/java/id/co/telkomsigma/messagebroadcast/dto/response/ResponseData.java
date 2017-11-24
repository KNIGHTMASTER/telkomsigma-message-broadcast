package id.co.telkomsigma.messagebroadcast.dto.response;

import java.io.Serializable;

/**
 * Created on 11/23/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ResponseData implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -4711145007516516166L;

    private String responseCode;
    private String responseMsg;

    public ResponseData(String responseCode, String responseMsg) {
        this.responseCode = responseCode;
        this.responseMsg = responseMsg;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
