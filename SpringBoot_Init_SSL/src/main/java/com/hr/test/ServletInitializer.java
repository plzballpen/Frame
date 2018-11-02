package com.hr.test;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TestApplication.class);
	}

	/*
	 * 스프링부트 2.0에서는 EmbeddedServletContainerFactory 와 TomcatEmbeddedServletContainerFactory이 대체됨
1. 
server.port:8080
#키 저장소 경로
server.ssl.key-store: classpath:keystore.p12
#키 저장소        
server.ssl.key-store-password: 123456
#alias 비밀번호, 별칭        
server.ssl.keyStoreType: PKCS12
server.ssl.keyAlias: tomcat

2.key는 개인 인증을 사용
	 * */
	
}
