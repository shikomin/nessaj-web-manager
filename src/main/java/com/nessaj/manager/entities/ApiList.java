package com.nessaj.manager.entities;

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
