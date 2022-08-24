package com.careyq.explore.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
public interface AttachmentService extends IService<Attachment> {

    /**
     * 上传文件
     *
     * @param file     文件
     * @param pathEnum FilePathEnum
     * @return 文件地址
     */
    String uploadFile(MultipartFile file, FilePathEnum pathEnum);
}
