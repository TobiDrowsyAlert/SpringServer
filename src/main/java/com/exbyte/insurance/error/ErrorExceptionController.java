package com.exbyte.insurance.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.exbyte.insurance.admin.exception.AdminNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ErrorExceptionController {
	
    @ExceptionHandler(AdminNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleAdminNotFoundException(AdminNotFoundException e) {
        final ErrorCode accountNotFound = ErrorCode.ADMIN_NOT_FOUND;
        log.error(accountNotFound.getMessage(), e.getId());
        return buildError(accountNotFound);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgoumentNotValidException(MethodArgumentNotValidException e) {
    	final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
    	return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, fieldErrors);
    }

    
    private List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult){
    	final List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.parallelStream()
                .map(error -> ErrorResponse.FieldError.builder()
                        .reason(error.getDefaultMessage())
                        .field(error.getField())
                        .value((String) error.getRejectedValue())
                        .build())
                .collect(Collectors.toList());
    	
    }
    
    private ErrorResponse buildError(ErrorCode errorCode) {
    	return ErrorResponse.builder()
    			.message(errorCode.getMessage())
    			.code(errorCode.getCode())
    			.status(errorCode.getStatus())
    			.build();
    }
    
    private ErrorResponse buildFieldErrors(ErrorCode errorCode, List<ErrorResponse.FieldError> errors){
    	return ErrorResponse.builder()
    			.message(errorCode.getMessage())
    			.code(errorCode.getCode())
    			.status(errorCode.getStatus())
    			.errors(errors)
    			.build();
    }
}
