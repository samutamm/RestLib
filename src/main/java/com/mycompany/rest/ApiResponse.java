package com.mycompany.rest;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import com.mycompany.domain.MongoSavable;
import com.mycompany.domain.Token;

import java.io.IOException;
import java.nio.charset.Charset;

public class ApiResponse {

    private HttpResponse response;
    private Gson gson;

    public ApiResponse(HttpResponse response) {
        this.gson = new Gson();
        this.response = response;
    }

    public boolean ok() {
        return response.getStatusLine().getStatusCode() == 200;
    }

    public MongoSavable[] body(MongoSavable[] domainClass) throws IOException {
        if (this.ok()) {
            String responseBodyAsJson = IOUtils.toString(response.getEntity()
                    .getContent(), Charset.forName("UTF-8"));
            return gson.fromJson(responseBodyAsJson, domainClass.getClass());
        } else {
            return null;
        }
    }
}
