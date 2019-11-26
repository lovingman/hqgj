package com.huacainfo.ace.common.exception;


/**
 * 自定义异常
 *
 * @author ArvinZou
 */
public class CustomException extends RuntimeException {

    /**
     * 返回状态码
     */
    private Integer errorCode;
    /**
     * 错误描述
     */
    private String errorMsg;
    /**
     * 详细描述
     */
    private String description;

    public CustomException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }


    public CustomException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public CustomException(Integer errorCode, String message, String description) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
        this.description = description;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getExceptionDetail() {
        return errorCode + "-[" + errorMsg + "]," + description;
    }
}
