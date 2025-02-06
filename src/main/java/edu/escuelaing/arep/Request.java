package edu.escuelaing.arep;

import java.util.Map;

public class Request {
    private final Map<String, String> queryParams;

    public Request(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public String getValues(String key) {
        return queryParams.getOrDefault(key, "");
    }
}
