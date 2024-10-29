package com.random.common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Slf4j
public class MyInterceptor implements HandlerInterceptor{
	private Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	private String GATEWAY_URI;
	private String ACTIVE_PROFILE;

	public MyInterceptor(String GATEWAY_URI, String ACTIVE_PROFILE) {
		this.GATEWAY_URI = GATEWAY_URI;
		this.ACTIVE_PROFILE = ACTIVE_PROFILE;
	}

	/**
	 * <h3>요청이 들어올 때마다 실행되는 메서드입니다.</h3>
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("===============================================");
        log.info("==================== BEGIN ====================");
        Enumeration eHeader = request.getHeaderNames();
    	while (eHeader.hasMoreElements()) {
	    	String key = (String)eHeader.nextElement();
	    	String value = request.getHeader(key);
	    	log.info("key : " + key + " ===> value : " + value);
	    }

		String requestUri = request.getHeader("x-forwarded-host");

		System.out.println("ACTIVE_PROFILE : " + ACTIVE_PROFILE);
		System.out.println("GATEWAY_URI : " + GATEWAY_URI);
		if(ACTIVE_PROFILE.equals("local")) {
			System.out.println("로컬에서 실행중입니다.");
			//GATEWAY_URI = "http://localhost:8080";
		}else{
			System.out.println("요청_URI : " + requestUri);
			if(("".equals(requestUri)||requestUri == null||!requestUri.equals(GATEWAY_URI))) {
				throw new BadCredentialsException("잘못된 접근입니다.");
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	/**
	 * <h3>요청이 끝날때 호출됩니다.</h3>
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("==================== END ======================");
        log.info("===============================================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
