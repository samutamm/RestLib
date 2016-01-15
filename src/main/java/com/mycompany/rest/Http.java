package com.mycompany.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import com.mycompany.domain.Token;


public class Http {

    private String endpointForPersons = "http://localhost:4567/persons";
    private String endpointForTokens = "http://localhost:4567/tokens";
    private String endpointForProducts = "http://localhost:4570/products";
    private Token token = new Token("invalid");

    public String endpointForPersons() {
        return endpointForPersons;
    }

    public String endpointForProducts() {
        return endpointForProducts;
    }

    public String endpointForTokens() {
        return endpointForTokens;
    }

    public ApiResponse get(String url) throws Exception {
        HttpResponse response = Request.Get(url)
                .addHeader("Authorization", token.toString())
                .execute().returnResponse();
        return new ApiResponse(response);
    }
}
