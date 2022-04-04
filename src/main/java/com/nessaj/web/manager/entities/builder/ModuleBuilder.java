package com.nessaj.web.manager.entities.builder;

import com.nessaj.web.manager.common.enums.ModuleStatus;
import com.nessaj.web.manager.common.enums.ModuleType;
import com.nessaj.web.manager.entities.ApiList;
import com.nessaj.web.manager.entities.Module;

import java.util.Date;
import java.util.List;

/**
 * @author keming
 * @Date 2022/04/04 19:09
 */
public class ModuleBuilder {

    private Module module;

    public ModuleBuilder() {
        this(new Module());
    }

    public ModuleBuilder(Module module) {
        this.module = module;
    }

    public ModuleBuilder setMname(String mname) {
        module.setMname(mname);
        return this;
    }

    public ModuleBuilder setType(ModuleType type) {
        module.setType(type);
        return this;
    }

    public ModuleBuilder setDescription(String description) {
        module.setDescription(description);
        return this;
    }

    public ModuleBuilder setStatus(ModuleStatus status) {
        module.setStatus(status);
        return this;
    }

    public ModuleBuilder setApis(ApiList apis) {
        module.setApis(apis);
        return this;
    }

    public ModuleBuilder setCreateTime(Date createTime) {
        module.setCreateTime(createTime);
        return this;
    }

    public Module build() {
        return this.module;
    }

}
