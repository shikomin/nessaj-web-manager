package com.nessaj.manager.mapper;

import com.nessaj.manager.entities.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper {

    Integer addModule(Module module);

    List<Module> findAllModule();

    Module findModuleById(Integer mid);

}
