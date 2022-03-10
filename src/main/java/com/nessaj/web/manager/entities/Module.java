package com.nessaj.web.manager.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Module implements Serializable {

    private int mid;
    private String mname;
    private String minfo;
    private String status;

}
