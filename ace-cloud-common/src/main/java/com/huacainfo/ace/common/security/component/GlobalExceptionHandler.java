package com.huacainfo.ace.common.security.component;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.dto.ResponseDTO;
import com.huacainfo.ace.common.tools.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 异常拦截处理器
 *
 * @author chenxiaoke
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 运行时异常
     *
     * @param ex RuntimeException
     * @return String
     */
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler(RuntimeException ex) {
        logger.error("{}", ex);
        return resultFormat(1, ex);
    }

    /**
     * 空指针异常
     *
     * @param ex NullPointerException
     * @return String
     */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException ex) {
        logger.error("{}", ex);
        return resultFormat(2, ex);
    }

    /**
     * 类型转换异常
     *
     * @param ex ClassCastException
     * @return String
     */
    @ExceptionHandler(ClassCastException.class)
    public String classCastExceptionHandler(ClassCastException ex) {
        logger.error("{}", ex);
        return resultFormat(3, ex);
    }

    /**
     * IO异常
     *
     * @param ex IOException
     * @return String
     */
    @ExceptionHandler(IOException.class)
    public String iOExceptionHandler(IOException ex) {
        logger.error("{}", ex);
        return resultFormat(4, ex);
    }

    /**
     * 未知方法异常
     *
     * @param ex NoSuchMethodException
     * @return String
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        logger.error("{}", ex);
        return resultFormat(5, ex);
    }

    /**
     * 数组越界异常
     *
     * @param ex IndexOutOfBoundsException
     * @return String
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        logger.error("{}", ex);
        return resultFormat(6, ex);
    }

    /**
     * 400错误
     *
     * @param ex HttpMessageNotReadableException
     * @return String
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public String requestNotReadable(HttpMessageNotReadableException ex) {
        logger.error("{}", ex);
        return resultFormat(7, ex);
    }

    /**
     * 400错误
     *
     * @param ex TypeMismatchException
     * @return String
     */
    @ExceptionHandler({TypeMismatchException.class})
    public String requestTypeMismatch(TypeMismatchException ex) {
        logger.error("{}", ex);
        return resultFormat(8, ex);
    }

    /**
     * 400错误
     *
     * @param ex MissingServletRequestParameterException
     * @return String
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
        logger.error("{}", ex);
        return resultFormat(9, ex);
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String request405(HttpRequestMethodNotSupportedException ex) {
        logger.error("{}", ex);
        return resultFormat(10, ex);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public String request406(HttpMediaTypeNotAcceptableException ex) {
        logger.error("{}", ex);
        return resultFormat(11, ex);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public String server500(RuntimeException ex) {
        logger.error("{}", ex);
        return resultFormat(12, ex);
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public String requestStackOverflow(StackOverflowError ex) {
        logger.error("{}", ex);
        return resultFormat(13, ex);
    }

    //其他错误
    @ExceptionHandler({Exception.class})
    public String exception(Exception ex) {
        logger.error("{}", ex);
        return resultFormat(14, ex);
    }

    private <T extends Throwable> String resultFormat(Integer code, T ex) {
        logger.info("{}", ex.getMessage());
        ResponseDTO<Integer> rst = new ResponseDTO(ResultCode.FAIL, ex.getMessage(), code);
        return JsonUtil.toJson(rst);
    }

}
