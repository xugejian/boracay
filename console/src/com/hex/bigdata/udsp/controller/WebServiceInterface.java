package com.hex.bigdata.udsp.controller;

import com.hex.bigdata.udsp.model.WSRequest;
import com.hex.bigdata.udsp.model.WSResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by PC on 2017/6/6.
 */
@WebService
public interface WebServiceInterface {

    @WebMethod
    String welcome();

    @WebMethod
    WSResponse consume(WSRequest request);

    @WebMethod
    String consume2(String json);

}
