package com.hr.test.rest.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.hr.test.rest.controller" }, useDefaultFilters = false, includeFilters = { @Filter(Controller.class),  @Filter(ControllerAdvice.class) })
public class RestAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer
		.useJaf(true)
		.favorPathExtension(true)
		.favorParameter(false)
		.ignoreAcceptHeader(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML); 
	}

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		//configureMessageConverters를 오버라이드한 후, converters를 이용해 생성한 mappingJacksonHttpMessageConverter를 추가한다.
		converters.add(mappingJacksonHttpMessageConverter());
		//XML
		converters.add(marshallingHttpMessageConverter());
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		//JSON 예쁘게 출력하기
		converter.setPrettyPrint(true);
		return converter;
	}
	
	@Bean 
	public MarshallingHttpMessageConverter marshallingHttpMessageConverter() {
		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setMarshaller(jaxb2Marshaller());
		converter.setUnmarshaller(jaxb2Marshaller());
		return converter;
	}

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		
		//Xmll 이 들어가있는 domain 등록
		marshaller.setPackagesToScan(new String[]{
				"com.hr.test.common.domain",
				"com.hr.test.rest.domain",
				"org.springframework.hateoas"});
		
		Map<String, Object> marshallerProperties = new HashMap<String, Object>();
		//XML 예쁘게 출력하기 
		marshallerProperties.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setMarshallerProperties(marshallerProperties);

		return marshaller;
	}
}
