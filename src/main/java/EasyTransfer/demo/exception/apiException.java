package EasyTransfer.demo.exception;

import lombok.Data;

import java.util.Date;

@Data
public class apiException {
    int status;
    String message;
    long timestamp;
    String path;

    public apiException(int status, String message, String path) {
        super();
        this.status = status;
        this.message = message;
        this.timestamp = new Date().getTime();
        this.path = path;
    }

}
