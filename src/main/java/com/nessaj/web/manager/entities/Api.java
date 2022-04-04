package com.nessaj.web.manager.entities;

import com.nessaj.web.manager.entities.builder.ApiBuilder;
import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author keming
 * @Date 2022/04/04 21:44
 */
@Getter
@Setter
@ToString
public class Api {

    private String mapping;
    private HttpMethod method;
    private String description;

    public static ApiBuilder custom() {
        return new ApiBuilder();
    }

}
