package com.hs.cxf.handler;

import org.apache.cxf.helpers.IOUtils;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.ByteArrayOutputStream;
import java.util.Set;

/**
 * Created by husong on 2018/8/30.
 */
public class AbstractHandler implements SOAPHandler<SOAPMessageContext>{

    public static final ThreadLocal<String> inputXML = new ThreadLocal<String>();
    public static final ThreadLocal<String> outputXML = new ThreadLocal<String>();

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        System.out.println("handleMessage执行。。。。");
        SOAPMessage soapMessage = context.getMessage();
        //响应消息
        Boolean isOut = (Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            soapMessage.writeTo(os);
            String str = IOUtils.newStringFromBytes(os.toByteArray());
            if(!isOut){
                setInputXML(str);
                System.out.println(str);
            }else{
                outputXML.set(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }

    public static void setInputXML(String input) {
        inputXML.set(input);
    }

    public static String getInputXML() {
        return inputXML.get();
    }

    public static void setOutputXML(String output) {
        outputXML.set(output);
    }

    public static String getOutputXML() {
        return outputXML.get();
    }
}
