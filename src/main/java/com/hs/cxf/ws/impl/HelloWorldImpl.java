package com.hs.cxf.ws.impl;

import com.hs.cxf.handler.AbstractHandler;
import com.hs.cxf.ws.HelloWorld;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by husong on 2018/8/30.
 */
@WebService(endpointInterface = "com.hs.cxf.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String sayHi(@WebParam(name = "name") String text) {
        System.out.println("sayHi执行。。。。");
        String str = AbstractHandler.getInputXML();
        return "hello "+text+"!";
    }
}
