package com.nessaj.web.manager.entities;

import com.nessaj.web.manager.common.enums.OperationResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleMessage {

    private String message;

    private OperationResult operationResult;

    public ModuleMessage() {
    }

    public ModuleMessage(String message) {
        this.operationResult = null;
        this.message = message;
    }

    public ModuleMessage(OperationResult operationResult, String message) {
        this.operationResult = operationResult;
        this.message = message;
    }

}
