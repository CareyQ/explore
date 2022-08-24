package com.careyq.explore.server.controller;

import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/file")
public class AttachmentController {

    private final AttachmentService fileService;

    /**
     * 上传头像
     *
     * @param file 头像文件
     * @return 结果
     */
    @PostMapping("/upload/avatar")
    public Result<Boolean> uploadAvatar(@RequestPart MultipartFile file) {
        return Result.success(fileService.uploadFile(file, FilePathEnum.AVATAR));
    }
}

