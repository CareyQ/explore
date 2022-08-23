package com.careyq.explore.server.controller;

import com.careyq.explore.server.service.ArticleTagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章标签关联表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/articleTag")
public class ArticleTagController {

    private final ArticleTagService articleTagService;

}

