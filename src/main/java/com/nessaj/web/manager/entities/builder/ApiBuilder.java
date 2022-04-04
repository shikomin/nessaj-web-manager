package com.nessaj.web.manager.entities.builder;

import com.nessaj.web.manager.entities.Api;
import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;

/**
 * @author keming
 * @Date 2022/04/04 22:50
 */
public class ApiBuilder {

    private Api api;

    public ApiBuilder() {
        this(new Api());
    }

    public ApiBuilder(Api api) {
        this.api = api;
    }

    public ApiBuilder setMapping(String mapping) {
        api.setMapping(mapping);
        return this;
    }

    public ApiBuilder setMethod(String method) {
        if (method.equals("get")) {
            api.setMethod(HttpMethod.GET);
        } else if (method.equals("post")) {
            api.setMethod(HttpMethod.POST);
        } else if (method.equals("put")) {
            api.setMethod(HttpMethod.PUT);
        } else if (method.equals("delete")) {
            api.setMethod(HttpMethod.DELETE);
        }
        return this;
    }

    public ApiBuilder setDescription(String description) {
        api.setDescription(description);
        return this;
    }

    public Api build() {
        return api;
    }

}
