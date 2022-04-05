package com.nessaj.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nessaj.manager.common.constants.TempConstants;
import com.nessaj.manager.common.enums.ModuleStatus;
import com.nessaj.manager.common.enums.ModuleType;
import com.nessaj.manager.entities.Api;
import com.nessaj.manager.entities.ApiList;
import com.nessaj.manager.entities.Module;
import com.nessaj.manager.mapper.ModuleMapper;
import com.nessaj.manager.service.ModuleService;
import com.nessaj.manager.common.constants.ErrorMessage;
import com.nessaj.sdk.common.constants.SymbolSet;
import com.nessaj.sdk.common.exception.UnzipFileException;
import com.nessaj.sdk.httpclient.common.constants.StringConstants;
import com.nessaj.sdk.utils.JsonUtil;
import com.nessaj.sdk.utils.ZipArchiveUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

    private static final Logger logger = Logger.getLogger(ModuleServiceImpl.class);

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> findAllModule() {
        return moduleMapper.findAllModule();
    }

    @Override
    public Module findModuleById(Integer mid) {
        return moduleMapper.findModuleById(mid);
    }

    @Override
    public boolean createModule(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // 上传包
        File dest = new File(TempConstants.MODULE_LOCAL_STORAGE
                + StringConstants.SEPARATOR_RIGHT + fileName);
        String unzipDir = TempConstants.MODULE_UNZIP_PATH + fileName.substring(0, fileName.lastIndexOf(".")) + SymbolSet.RIGHT_SEPARATOR;
        (new File(unzipDir)).mkdir();
        try {
            file.transferTo(dest);
            ZipArchiveUtil.unZip(dest.getAbsolutePath(), unzipDir);
        } catch (IOException e) {
            logger.error(ErrorMessage.FAILED_TO_SAVE_MODULE + fileName, e);
        } catch (UnzipFileException e) {
            e.printStackTrace();
        }
        JSONObject moduleJson = JsonUtil.str2json(JsonUtil.str2json(JsonUtil.readJsonFile(unzipDir
                        + SymbolSet.RIGHT_SEPARATOR
                        + TempConstants.MODULE_JSON))
                .getString(TempConstants.MODULE_JSON_KEY));
        Module module = Module.custom().setType(moduleJson.getString(TempConstants.MODULE_JSON_KEY_TYPE).equals(TempConstants.JAVA_MODULE) ? ModuleType.JAVA_MODULE : ModuleType.PYTHON_MODULE)
                .setMname(moduleJson.getString(TempConstants.MODULE_JSON_KEY_NAME))
                .setDescription(moduleJson.getString(TempConstants.MODULE_JSON_KEY_DESCRIPTION))
                .setApis(getApiList(moduleJson))
                .setStatus(ModuleStatus.UPLOADED)
                .setCreateTime(new Date()).build();
        if (moduleMapper.addModule(module) > 0) {
            return true;
        }
        return false;
    }

    private ApiList getApiList(JSONObject module) {
        JSONObject apisJson = JsonUtil.str2json(module.getString(TempConstants.APIS_JSON_KEY));
        List<Api> apis = new ArrayList<>();
        for (Map.Entry entry : apisJson.entrySet()) {
            JSONObject api = JsonUtil.str2json(entry.getValue().toString());
            apis.add(Api.custom().setMapping(api.getString(TempConstants.API_JSON_KEY_MAPPING))
                    .setDescription(api.getString(TempConstants.API_JSON_KEY_DESCRIPTION))
                    .setMethod(api.getString(TempConstants.API_JSON_KEY_METHOD)).build());
        }
        ApiList apiList = new ApiList();
        apiList.setApis(apis);
        apiList.setAid(randomNumber());
        return apiList;
    }

    //TODO 临时用随机数生成id，之后修改
    private int randomNumber() {
        return (int) (System.currentTimeMillis() % 1000);
    }

}
