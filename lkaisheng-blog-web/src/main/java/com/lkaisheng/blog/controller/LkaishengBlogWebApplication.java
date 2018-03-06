package com.lkaisheng.blog.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.Charset;

@Controller
@SpringBootApplication
public class LkaishengBlogWebApplication {

	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("hello")
	@ResponseBody
	public String hello(){
		return "hello 欢迎光临李开生的博客";
	}

	public static void main(String[] args) {
		SpringApplication.run(LkaishengBlogWebApplication.class, args);
	}

//	自定义消息转换器
//	@Bean
//	public StringHttpMessageConverter stringHttpMessageConverter(){
//		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//		return converter;
//	}
}
