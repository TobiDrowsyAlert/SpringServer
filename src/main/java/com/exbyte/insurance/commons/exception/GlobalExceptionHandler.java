package com.exbyte.insurance.commons.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	/*
	 * @Validated로 binding error 시 발생,
	 * HttpMessageConverter로 등록한 HttpMessageConvertoer binding 못할 시 발생
	 * 주로 @RequestBody @ResquestPart 어노테이션에서 발생
	 */


}
