package com.zh.cxf.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.zh.cxf.HelloWorld;

@Component("helloWorld")
@WebService
public class HelloWorldImpl implements HelloWorld {
	public String say(String str) {
		return "Hello"+str;
	}
}