package com.hr.test.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hr.test.rest.domain.RestError;
import com.hr.test.rest.exception.ResourceNotFoundException;

//Exception 에러 정의
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

	//처리할 예외를 정의
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody   //body로 응답 결과를 반환
	public RestError handleResourceNotFound(ResourceNotFoundException ex){
		RestError error = new RestError(404, "해당 자원을 찾을 수 없습니다.");
		return error;
	}
}
