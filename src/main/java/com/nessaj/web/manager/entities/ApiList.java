package com.nessaj.web.manager.entities;

import com.nessaj.web.sdk.httpclient.common.enums.HttpMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author keming
 * @Date 2022/04/04 19:14
 */
@Getter
@Setter
@ToString
public class ApiList {

    private Integer aid;
    private List<Api> apis;

}
