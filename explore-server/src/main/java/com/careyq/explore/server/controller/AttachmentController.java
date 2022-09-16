package com.careyq.explore.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.AttachmentBatchOperateDTO;
import com.careyq.explore.server.dto.AttachmentPageDTO;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.service.AttachmentService;
import com.careyq.explore.server.service.AttachmentVO;
import com.careyq.explore.server.vo.AttachmentPageVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    private final AttachmentService attachmentService;

    /**
     * 上传头像
     *
     * @param file 头像文件
     * @return 结果
     */
    @PostMapping("/upload/avatar")
    public Result<Boolean> uploadAvatar(@RequestPart MultipartFile file) {
        return Result.success(attachmentService.uploadFile(file, FilePathEnum.AVATAR));
    }

    /**
     * 获取附件分页
     *
     * @param dto 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public Result<IPage<AttachmentPageVO>> getAttachmentPage(@RequestBody @Validated AttachmentPageDTO dto) {
        return Result.success(attachmentService.getAttachmentPage(dto));
    }

    /**
     * 获取附件详情
     *
     * @param id 附件 ID
     * @return 附件详情
     */
    @GetMapping("/detail")
    public Result<AttachmentVO> getAttachment(@RequestParam Long id) {
        return Result.success(attachmentService.getAttachment(id));
    }

    public Result<Boolean> batchOperate(@RequestBody @Validated AttachmentBatchOperateDTO dto) {
        return attachmentService.batchOperate(dto);
    }
}

