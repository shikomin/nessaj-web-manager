package com.nessaj.manager.controller;

import com.nessaj.manager.common.constants.ManagerConstant;
import com.nessaj.manager.common.enums.OperationResult;
import com.nessaj.manager.entities.Module;
import com.nessaj.manager.entities.ModuleMessage;
import com.nessaj.manager.service.ModuleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;


/**
 * @author keming
 * @Date 2022/02/07 18:55
 */
@RestController
public class ModuleController {

    private static final Logger LOGGER = Logger.getLogger(ModuleController.class);

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/module")
    public ResponseEntity<ModuleMessage> createModule(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            ModuleMessage res = new ModuleMessage(OperationResult.FAILED, ManagerConstant.UPLOAD_MODULE_FAILED);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }
        // TODO:2022/3/10 后期补充文件格式校验、完整性校验、签名校验等...
        if (moduleService.createModule(file)) {
            return new ResponseEntity<>(new ModuleMessage(OperationResult.SUCCESS, ManagerConstant.MODULE_CREATE_SUCCESS), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ModuleMessage(OperationResult.FAILED, ManagerConstant.ADD_MODULE_FAILED), HttpStatus.ACCEPTED);
    }

    @GetMapping("/modules")
    public ResponseEntity<List<Module>> findAllModule() {
        List<Module> modules = moduleService.findAllModule();
        Iterator<Module> moduleIterator = modules.listIterator();
        while (moduleIterator.hasNext()) {
            Module module = moduleIterator.next();
            LOGGER.debug(module.toString());
        }
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    @GetMapping("/module")
    public ResponseEntity<Module> findModuleById(Integer mid) {
        Module module = moduleService.findModuleById(mid);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    private ResponseEntity<ModuleMessage> addModule(Module module) {
//        if (moduleService.findModuleById(module.getMid()) != null) {
//            return new ResponseEntity<>(new ModuleMessage(OperationResult.FAILED, ManagerConstant.ADD_MODULE_FAILED), HttpStatus.ACCEPTED);
//        }
//        // todo
//        if (moduleService.createModule(module) != 1) {
//            return new ResponseEntity<>(new ModuleMessage(OperationResult.FAILED, ManagerConstant.ADD_MODULE_FAILED), HttpStatus.ACCEPTED);
//        }
//        return new ResponseEntity<>(new ModuleMessage(OperationResult.SUCCESS, ManagerConstant.MODULE_CREATE_SUCCESS), HttpStatus.OK);
        return null;
    }

}
