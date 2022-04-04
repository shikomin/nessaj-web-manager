package com.nessaj.web.manager.common.enums;

/**
 * @author keming
 * @Date 2022/04/04 19:34
 */
public enum ModuleType {

    PYTHON_MODULE(1), JAVA_MODULE(2);

    private int type;

    ModuleType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
