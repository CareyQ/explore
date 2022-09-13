package com.careyq.explore.server.controller;

import com.careyq.explore.server.service.AttachmentCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/attachmentCategory")
public class AttachmentCategoryController {

    private final AttachmentCategoryService attachmentCategoryService;

}

