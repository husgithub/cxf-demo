package com.hs.cxf.ws;

import javax.jws.WebService;

/**
 * Created by husong on 2018/8/30.
 */
@WebService
public interface HelloWorld {

    String sayHi(String text);
}
