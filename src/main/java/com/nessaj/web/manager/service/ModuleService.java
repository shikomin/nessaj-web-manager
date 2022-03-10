package com.nessaj.web.manager.service;

import com.nessaj.web.manager.entities.Module;

import java.util.List;

public interface ModuleService {

    Integer createModule(Module module);

    List<Module> findAllModule();

    Module findModuleById(Integer mid);

}
