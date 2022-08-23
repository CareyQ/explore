package com.careyq.explore.server.controller;

import com.careyq.explore.server.service.ArticleContentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章内容表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/articleContent")
public class ArticleContentController {

    private final ArticleContentService articleContentService;

}

