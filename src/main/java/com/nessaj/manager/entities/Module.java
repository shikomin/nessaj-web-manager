package com.nessaj.manager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nessaj.manager.common.enums.ModuleStatus;
import com.nessaj.manager.common.enums.ModuleType;
import com.nessaj.manager.entities.builder.ModuleBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Module implements Serializable {

    private Integer mid;
    private String mname;
    private ModuleType type;
    private ModuleStatus status;
    private ApiList apis;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public static ModuleBuilder custom() {
        return new ModuleBuilder();
    }

}
