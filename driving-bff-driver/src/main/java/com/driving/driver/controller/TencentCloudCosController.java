package com.driving.driver.controller;

import com.driving.common.exception.BusinessException;
import com.driving.common.util.CosUtil;
import com.driving.common.util.R;
import com.driving.driver.controller.form.DeleteCosFileForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author YueLiMin
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/tencent-cloud/cos")
@Tag(name = "TencentCloudCosController", description = "腾讯云对象存储服务web接口")
public class TencentCloudCosController {
    @Resource
    private CosUtil cosUtil;

    // @SaCheckLogin
    @PostMapping("/uploadCosPublicFile")
    @Operation(summary = "公共桶-上传文件")
    public R uploadCosPublicFile(@RequestParam(value = "module", required = false) String module, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException("上传文件为空, 请重新上传");
        }

        try {
            // module -> driver-public-banner
            // path -> /driver/public/banner/
            String path = "/" + module.replace("-", "/") + "/";

            HashMap<String, Object> map = cosUtil.uploadPublicFile(file, path);
            return R.ok(map);
        } catch (IOException e) {
            log.error("腾讯云对象存储服务文件上传失败", e);
            throw new BusinessException("腾讯云对象存储服务上传文件失败");
        }

    }

    // @SaCheckLogin
    @PostMapping("/uploadCosPrivateFile")
    @Operation(summary = "私有桶-上传文件")
    public R uploadCosPrivateFile(@RequestParam(value = "module", required = false) String module, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new BusinessException("上传文件为空, 请重新上传");
        }

        try {
            String path = "/" + module.replace("-", "/") + "/";
            HashMap<String, Object> map = cosUtil.uploadPrivateFile(file, path);
            return R.ok(map);
        } catch (IOException exception) {
            log.error("腾讯云对象存储服务文件上传失败", exception);
            throw new BusinessException("腾讯云对象存储服务上传文件失败");
        }
    }

    // @SaCheckLogin
    @DeleteMapping("/deleteCosPrivateFile")
    @Operation(summary = "私有桶-删除文件")
    public R deleteCosPrivateFile(@RequestBody @Valid DeleteCosFileForm deleteCosFileForm) {
        cosUtil.deletePrivateFile(deleteCosFileForm.getPaths());
        return R.ok();
    }

    // @SaCheckLogin
    @DeleteMapping("/deleteCosPublicFile")
    @Operation(summary = "公有桶-删除文件")
    public R deleteCosPublicFile(@RequestBody @Valid DeleteCosFileForm deleteCosFileForm) {
        cosUtil.deletePublicFile(deleteCosFileForm.getPaths());
        return R.ok();
    }
}
