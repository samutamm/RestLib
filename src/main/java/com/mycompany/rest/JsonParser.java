
package com.mycompany.rest;

import com.google.gson.Gson;
import com.mycompany.domain.MongoSavable;
import com.mycompany.domain.Token;
import com.mycompany.domain.Error;


public class JsonParser {
    private Gson gson;

    public JsonParser() {
        this.gson = new Gson();
    }
    
    public String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public MongoSavable[] fromJson(String responseBodyAsJson, Class<? extends MongoSavable[]> aClass) {
        return gson.fromJson(responseBodyAsJson, aClass);
    }

    public Token tokenFromJson(String json) {
        return gson.fromJson(json, Token.class);
    }

    public Error errorFromJson(String json) {
        System.out.println(json);
        return gson.fromJson(json, Error.class);
    }
    
}
