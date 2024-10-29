package com.random.common.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResponseAdvice {
	private Logger logger = LoggerFactory.getLogger(ErrorResponseAdvice.class);
}
