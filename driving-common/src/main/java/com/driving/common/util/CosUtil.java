package com.driving.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.driving.common.exception.BusinessException;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingRequest;
import com.qcloud.cos.model.ciModel.auditing.ImageAuditingResponse;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Component
public class CosUtil {
    @Value("${tencent.cloud.appId}")
    private String appId;

    @Value("${tencent.cloud.secretId}")
    private String secretId;

    @Value("${tencent.cloud.secretKey}")
    private String secretKey;

    @Value("${tencent.cloud.region-public}")
    private String regionPublic;

    @Value("${tencent.cloud.bucket-public}")
    private String bucketPublic;

    @Value("${tencent.cloud.region-private}")
    private String regionPrivate;

    @Value("${tencent.cloud.bucket-private}")
    private String bucketPrivate;

    /**
     * 获取访问公有存储桶的连接
     *
     * @return
     */
    private COSClient getCosPublicClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(regionPublic));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    /**
     * 获得访问私有存储桶的连接
     *
     * @return
     */
    private COSClient getCosPrivateClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(regionPrivate));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    /**
     * 向公有存储桶上传文件
     */
    public HashMap<String, Object> uploadPublicFile(MultipartFile file, String path) throws IOException {
        // 上传文件的名字
        String fileName = file.getOriginalFilename();
        // 文件后缀名
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        // 避免重名图片在云端覆盖, 所以用UUID作为文件名
        path += IdUtil.simpleUUID() + fileType;

        // 元数据信息
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(file.getSize());
        meta.setContentEncoding("UTF-8");
        meta.setContentType(file.getContentType());

        // 向存储桶中保存文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketPublic, path, file.getInputStream(), meta);
        putObjectRequest.setStorageClass(StorageClass.Standard); // 标准存储
        COSClient client = getCosPublicClient();
        PutObjectResult putObjectResult = client.putObject(putObjectRequest);

        // 合成外网访问地址
        HashMap<String, Object> map = new HashMap<>();
        map.put("url", "https://" + bucketPublic + ".cos." + regionPublic + ".myqcloud.com" + path);
        map.put("path", path);

        // 如果保存的是图片, 用数据万象服务对图片内容审核
        if (List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp").contains(fileType)) {
            // 审核图片内容
            ImageAuditingRequest request = new ImageAuditingRequest();
            request.setBucketName(bucketPublic);
            // 辨别黄色, 暴利, 政治和广告内容
            request.setDetectType("porn,terrorist,politics,ads");
            request.setObjectKey(path);
            // 执行审查
            ImageAuditingResponse response = client.imageAuditing(request);

            if (!response.getPornInfo().getHitFlag().equals("0")
                    || !response.getTerroristInfo().getHitFlag().equals("0")
                    || !response.getAdsInfo().getHitFlag().equals("0")
            ) {
                // 删除违规图片
                client.deleteObject(bucketPublic, path);
                throw new BusinessException("图片内容不合规");
            }
        }
        client.shutdown();
        return map;
    }

    /**
     * 向私有存储桶上传文件
     */
    public HashMap<String, Object> uploadPrivateFile(MultipartFile file, String path) throws IOException {
        // 上传文件的名字
        String fileName = file.getOriginalFilename();
        // 文件后缀名
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        // 避免重名图片在云端覆盖, 所以用UUID作为文件名
        path += IdUtil.simpleUUID() + fileType;

        // 元数据信息
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentLength(file.getSize());
        meta.setContentEncoding("UTF-8");
        meta.setContentType(file.getContentType());

        // 向存储桶中保存文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketPrivate, path, file.getInputStream(), meta);
        putObjectRequest.setStorageClass(StorageClass.Standard);
        COSClient client = getCosPrivateClient();
        // 上传文件
        PutObjectResult putObjectResult = client.putObject(putObjectRequest);

        HashMap<String, Object> map = new HashMap<>();
        map.put("path", path);

        // 如果保存的是图片, 用数据万象服务对图片内容审核
        if (List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp").contains(fileType)) {
            // 审核图片内容
            ImageAuditingRequest request = new ImageAuditingRequest();
            request.setBucketName(bucketPrivate);
            // 辨别黄色, 暴利, 政治和广告内容
            request.setDetectType("porn,terrorist,politics,ads");
            request.setObjectKey(path);
            // 执行审查
            ImageAuditingResponse response = client.imageAuditing(request);

            if (!response.getPornInfo().getHitFlag().equals("0")
                    || !response.getTerroristInfo().getHitFlag().equals("0")
                    || !response.getPoliticsInfo().getHitFlag().equals("0")
                    || !response.getAdsInfo().getHitFlag().equals("0")
            ) {
                // 删除违规图片
                client.deleteObject(bucketPrivate, path);
                throw new BusinessException("图片内容不合规");
            }
        }
        client.shutdown();
        return map;

    }

    /**
     * 获取私有读写文件的临时URL外网访问地址
     */
    public String getPrivateFileUrl(String path) {
        COSClient client = getCosPrivateClient();
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketPrivate, path, HttpMethodName.GET);
        // 设置临时URL有效期为5分钟
        Date expiration = DateUtil.offsetMinute(new Date(), 5);
        request.setExpiration(expiration);
        URL url = client.generatePresignedUrl(request);
        client.shutdown();
        return url.toString();
    }

    /**
     * 刪除公有存储桶的文件
     */
    public void deletePublicFile(String[] pathes) {
        COSClient client = getCosPublicClient();
        for (String path : pathes) {
            client.deleteObject(bucketPublic, path);
        }
        client.shutdown();
    }

    /**
     * 刪除私有存储桶的文件
     */
    public void deletePrivateFile(String[] pathes) {
        COSClient client = getCosPrivateClient();
        for (String path : pathes) {
            client.deleteObject(bucketPrivate, path);
        }
        client.shutdown();
    }

}