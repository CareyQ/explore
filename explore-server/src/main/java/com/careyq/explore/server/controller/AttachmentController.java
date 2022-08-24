package com.careyq.explore.server.controller;

import com.careyq.explore.server.service.AttachmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

