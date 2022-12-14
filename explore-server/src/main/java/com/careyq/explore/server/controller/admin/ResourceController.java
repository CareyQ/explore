package com.careyq.explore.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.AttachmentBatchOperateDTO;
import com.careyq.explore.server.dto.ResourcePageDTO;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.service.ResourceService;
import com.careyq.explore.server.vo.ResourceVO;
import com.careyq.explore.server.vo.ResourcePageVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 资源表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/resource")
public class ResourceController {

    private final ResourceService resourceService;

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 结果
     */
    @PostMapping("/upload")
    public Result<Boolean> upload(@RequestPart MultipartFile file, Long categoryId) {
        return Result.success(resourceService.uploadFile(file, categoryId, FilePathEnum.RESOURCE));
    }

    /**
     * 批量上传文件
     *
     * @param files 文件
     * @return 结果
     */
    @PostMapping("/batch/upload")
    public Result<Boolean> batchUpload(@RequestPart List<MultipartFile> files, Long categoryId) {
        return Result.success(resourceService.batchUploadFile(files, categoryId), "上传成功");
    }

    /**
     * 上传头像
     *
     * @param file 头像文件
     * @return 结果
     */
    @PostMapping("/upload/avatar")
    public Result<Boolean> uploadAvatar(@RequestPart MultipartFile file) {
        return Result.success(resourceService.uploadFile(file, 1L, FilePathEnum.AVATAR));
    }

    /**
     * 获取附件分页
     *
     * @param dto 分页参数
     * @return 分页结果
     */
    @PostMapping("/page")
    public Result<IPage<ResourcePageVO>> getPage(@RequestBody @Validated ResourcePageDTO dto) {
        return Result.success(resourceService.getPage(dto));
    }

    /**
     * 获取附件详情
     *
     * @param id 附件 ID
     * @return 附件详情
     */
    @GetMapping("/detail")
    public Result<ResourceVO> getAttachment(@RequestParam Long id) {
        return Result.success(resourceService.getDetail(id));
    }

    public Result<Boolean> batchOperate(@RequestBody @Validated AttachmentBatchOperateDTO dto) {
        return resourceService.batchOperate(dto);
    }
}

