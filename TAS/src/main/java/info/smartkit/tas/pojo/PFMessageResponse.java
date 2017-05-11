package info.smartkit.tas.pojo;

import java.lang.reflect.Array;

/**
 * Created by smartkit on 11/05/2017.
 */
public class PFMessageResponse {
    private int success;
    private String errorMessage;
    private MessageResponse message;
    private Object data;

    public PFMessageResponse() {
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MessageResponse getMessage() {
        return message;
    }

    public void setMessage(MessageResponse message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PFMessageResponse{" +
                "success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                ", message=" + message +
                ", data=" + data +
                '}';
    }
}
