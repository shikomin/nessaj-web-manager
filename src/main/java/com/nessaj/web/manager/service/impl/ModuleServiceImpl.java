package com.nessaj.web.manager.service.impl;

import com.nessaj.web.manager.entities.Module;
import com.nessaj.web.manager.mapper.ModuleMapper;
import com.nessaj.web.manager.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public Integer createModule(Module module) {
        return moduleMapper.createModule(module);
    }

    @Override
    public List<Module> findAllModule() {
        return moduleMapper.findAllModule();
    }

    @Override
    public Module findModuleById(Integer mid) {
        return moduleMapper.findModuleById(mid);
    }

}
