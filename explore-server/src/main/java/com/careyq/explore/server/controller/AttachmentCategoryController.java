package com.careyq.explore.server.controller;

import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.AttachmentCategory;
import com.careyq.explore.server.service.AttachmentCategoryService;
import com.careyq.explore.server.vo.AttachmentCategoryPageVO;
import com.careyq.explore.server.vo.AttachmentCategoryVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文件分类表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/attachment/category")
public class AttachmentCategoryController {

    private final AttachmentCategoryService attachmentCategoryService;

    /**
     * 保存文件分类
     *
     * @param category 文件分类
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> saveAttachmentCategory(@RequestBody @Validated AttachmentCategory category) {
        return attachmentCategoryService.saveAttachmentCategory(category);
    }

    /**
     * 获取文件分类
     *
     * @return 结果
     */
    @GetMapping("/list")
    public Result<List<AttachmentCategoryPageVO>> getAttachmentCategoryList() {
        return Result.success(attachmentCategoryService.getAttachmentCategoryList());
    }

    /**
     * 获取文件分类详情
     *
     * @param id 分类 ID
     * @return 详情
     */
    @GetMapping("/detail")
    public Result<AttachmentCategoryVO> getAttachmentDetail(@RequestParam Long id) {
        return Result.success(attachmentCategoryService.getAttachmentDetail(id));
    }
}

