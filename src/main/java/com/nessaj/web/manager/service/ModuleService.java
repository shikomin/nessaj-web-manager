package com.nessaj.web.manager.service;

import com.nessaj.web.manager.entities.Module;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ModuleService {

    boolean createModule(MultipartFile file);

    List<Module> findAllModule();

    Module findModuleById(Integer mid);

}
