package com.gowsow.shiba.backend.errorhandle;

import com.gowsow.shiba.backend.dto.CustomErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<Object> handleCustomException(CustomException e, WebRequest request) {
        logger.info("handleCustomException , error: ", e);
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(new Date(), e.getMessage());
        HttpHeaders headers = new HttpHeaders();
        switch (e.getStatus()) {
            case BAD_REQUEST:
                return handleExceptionInternal(e, customErrorMessage, headers, BAD_REQUEST, request);
            case UNAUTHORIZED:
                return handleExceptionInternal(e, customErrorMessage, headers, UNAUTHORIZED, request);
            case NOT_FOUND:
                return handleExceptionInternal(e, customErrorMessage, headers, NOT_FOUND, request);
            case CONFLICT:
                return handleExceptionInternal(e, customErrorMessage, headers, CONFLICT, request);
            case FORBIDDEN:
                return handleExceptionInternal(e, customErrorMessage, headers, FORBIDDEN, request);
            case INTERNAL_SERVER_ERROR:
                return handleExceptionInternal(e, customErrorMessage, headers, INTERNAL_SERVER_ERROR, request);
            default:
                logger.warn("handleException , but we don't handle for now");
                break;
        }
        return handleExceptionInternal(e, customErrorMessage, new HttpHeaders(), INTERNAL_SERVER_ERROR,
                request);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Object> handle(ValidationException e, WebRequest request) {
        logger.info("handleValidationException , error: ", e);
        HttpHeaders headers = new HttpHeaders();
        CustomErrorMessage customErrorMessage = new CustomErrorMessage(new Date(), e.getMessage());
        return handleExceptionInternal(e, customErrorMessage, headers, BAD_REQUEST, request);
    }

}