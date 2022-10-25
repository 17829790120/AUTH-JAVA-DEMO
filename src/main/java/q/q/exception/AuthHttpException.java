package q.q.exception;

/**
 * auth异常信息
 *
 * @author shawn
 * @since 2022/10/25 11:47
 */
public class AuthHttpException extends Exception {
    private int errCode;
    private String errMsg;

    public AuthHttpException() {
    }

    public AuthHttpException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}
