package com.nessaj.web.manager.controller;

import com.nessaj.web.manager.common.constants.ErrorMessage;
import com.nessaj.web.manager.common.constants.ManagerConstant;
import com.nessaj.web.manager.common.constants.TemporaryConstants;
import com.nessaj.web.manager.common.enums.OperationResult;
import com.nessaj.web.manager.entities.Module;
import com.nessaj.web.manager.entities.ModuleMessage;
import com.nessaj.web.manager.service.ModuleService;
import com.nessaj.web.sdk.httpclient.common.constants.StringConstants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author keming
 * @Date 2022/02/07 18:55
 */
@RestController
public class ModuleController {

    private static final Logger logger = Logger.getLogger(ModuleController.class);

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/module")
    public ResponseEntity<ModuleMessage> createModule(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            ModuleMessage res = new ModuleMessage(OperationResult.FAILED, ManagerConstant.UPLOAD_MODULE_FAILED);
            return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
        }
        String fileName = file.getOriginalFilename();
        // TODO:2022/3/10 后期需补文件格式校验、完整性校验、签名校验
        // 上传包
        File dest = new File(TemporaryConstants.MODULE_LOCAL_STORAGE
                + StringConstants.SEPARATOR_RIGHT + fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            logger.error(ErrorMessage.FAILED_TO_SAVE_MODULE + fileName, e);
        }
//持久化
//        Module newModule = new Module();
//        return addModule(newModule);
        logger.info(ManagerConstant.CREATE_MODULE_SUCCESS + fileName);
        ModuleMessage res = new ModuleMessage(OperationResult.SUCCESS, ManagerConstant.CREATE_MODULE_SUCCESS + fileName);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @GetMapping("/modules")
    public ResponseEntity<List<Module>> findAllModule() {
        List<Module> modules = moduleService.findAllModule();
        Iterator<Module> moduleIterator = modules.listIterator();
        while (moduleIterator.hasNext()) {
            Module module = moduleIterator.next();
            System.out.println(module.toString());
        }
        return new ResponseEntity<>(modules, HttpStatus.OK);
    }

    @GetMapping("/module")
    public ResponseEntity<Module> findModuleById(Integer mid) {
        Module module = moduleService.findModuleById(mid);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    private ResponseEntity<ModuleMessage> addModule(Module module) {
        if (moduleService.findModuleById(module.getMid()) != null) {
            return new ResponseEntity<>(new ModuleMessage(OperationResult.FAILED, ManagerConstant.ADD_MODULE_FAILED), HttpStatus.ACCEPTED);
        }
        // todo
        if (moduleService.createModule(module) != 1) {
            return new ResponseEntity<>(new ModuleMessage(OperationResult.FAILED, ManagerConstant.ADD_MODULE_FAILED), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new ModuleMessage(OperationResult.SUCCESS, ManagerConstant.MODULE_CREATE_SUCCESS), HttpStatus.OK);
    }

}
