package com.mycompany.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import com.mycompany.domain.Token;
import com.mycompany.domain.Person;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;


public class Http {

    private String endpointForPersons = "http://localhost:4567/persons";
    private String endpointForTokens = "http://localhost:4567/tokens";
    private String endpointForProducts = "http://localhost:4570/products";
    private String endpointForLogin = "http://localhost:4567/session";
    private Token token = new Token("invalid");
    private JsonParser parser;

    public Http() {
        this.parser = new JsonParser();
    }
    
    public String endpointForLogin() {
        return endpointForLogin;
    }

    public Token getToken() {
        return token;
    }
    
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
        return new ApiResponse(parser, response);
    }
    
    public ApiResponse post(String url, Object data) throws IOException {
        HttpResponse httpResponse = postJson(url, parser.toJson(data));
        return new ApiResponse(parser, httpResponse);
    }

    public ApiResponse login(String url, String username, String password) throws IOException {
        Person person = new Person(username, null, password, null);
        String asJson = parser.toJson(person);
        HttpResponse httpResponse = postJson(url, asJson);
        ApiResponse response = new ApiResponse(parser, httpResponse);
        if (response.ok()) {
            token = parser.tokenFromJson(response.getJson());
        }
        return response;
    }

    private HttpResponse postJson(String url, String asJson) throws IOException {
        HttpResponse httpResponse = Request.Post(url)
                .bodyString(asJson, ContentType.APPLICATION_JSON)
                .execute().returnResponse();
        return httpResponse;
    }
}
