package com.nessaj.web.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nessaj.web.manager.common.constants.ErrorMessage;
import com.nessaj.web.manager.common.constants.TempConstants;
import com.nessaj.web.manager.common.enums.ModuleStatus;
import com.nessaj.web.manager.common.enums.ModuleType;
import com.nessaj.web.manager.entities.Api;
import com.nessaj.web.manager.entities.ApiList;
import com.nessaj.web.manager.entities.Module;
import com.nessaj.web.manager.mapper.ModuleMapper;
import com.nessaj.web.manager.service.ModuleService;
import com.nessaj.web.sdk.common.exception.UnzipFileException;
import com.nessaj.web.sdk.httpclient.common.constants.StringConstants;
import com.nessaj.web.sdk.utils.JsonUtil;
import com.nessaj.web.sdk.utils.ZipArchiveUtil;
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
import java.util.Random;

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
        String unzipDir = TempConstants.MODULE_UNZIP_PATH + fileName.substring(0, fileName.lastIndexOf(".")) + TempConstants.RIGHT_SEPARATOR;
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
                        + TempConstants.RIGHT_SEPARATOR
                        + TempConstants.MODULE_JSON))
                .getString(TempConstants.MODULE_KEY));
        Module module = Module.custom().setType(moduleJson.getString("type").equals("java_module") ? ModuleType.JAVA_MODULE : ModuleType.PYTHON_MODULE)
                .setMname(moduleJson.getString("mname"))
                .setDescription(moduleJson.getString("description"))
                .setApis(getApiList(moduleJson))
                .setStatus(ModuleStatus.UPLOADED)
                .setCreateTime(new Date()).build();
        if (moduleMapper.addModule(module) > 0) {
            return true;
        }
        return false;
    }

    private ApiList getApiList(JSONObject module) {
        JSONObject apisJson = JsonUtil.str2json(module.getString("apis"));
        List<Api> apis = new ArrayList<>();
        for (Map.Entry entry : apisJson.entrySet()) {
            JSONObject api = JsonUtil.str2json(entry.getValue().toString());
            apis.add(Api.custom().setMapping(api.getString("mapping"))
                    .setDescription(api.getString("description"))
                    .setMethod(api.getString("method")).build());
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
