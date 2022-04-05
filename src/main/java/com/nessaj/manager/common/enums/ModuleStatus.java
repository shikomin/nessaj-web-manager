package com.nessaj.manager.common.enums;

/**
 * @author keming
 * @Date 2022/04/04 20:03
 */
public enum ModuleStatus {

    UPLOADED(1), START(2), RUNNING(3), STOP(4), DELETE(5);

    private int status;

    ModuleStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
